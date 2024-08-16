package archiver.huffman;

import archiver.Archiver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.IntConsumer;

public class Huffman implements Archiver {
    private static class InstanceHolder {
        static final Huffman INSTANCE = new Huffman();
    }
    public static Huffman getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private Huffman() {
    }

    public OutputStream compress(OutputStream out) throws IOException {
        return new BufferedOutputStream(new OutputStream() {
            private ObjectOutputStream os = new ObjectOutputStream(out);

            public void write(int b) {
                throw new IllegalStateException();
            }

            public synchronized void write(byte[] b, int off, int len) throws IOException {
                if (os == null)
                    throw new IOException("Stream Closed!");
                Objects.checkFromIndexSize(off, len, b.length);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(len);
                try {
                    encode(b, off, len, new HufConsumer() {
                        public void accept(NodeBase tree) {
                            try {
                                os.writeObject(tree);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } finally {
                                tree.delete();
                            }
                        }

                        public void accept(int value) {
                            bos.write(value);
                        }
                    });
                } catch (RuntimeException e) {
                    Throwable c = e.getCause();
                    if (c instanceof IOException)
                        throw ((IOException) c);
                    throw e;
                }

                os.writeObject(bos.toByteArray());
            }

            public synchronized void close() throws IOException {
                if (os == null)
                    return;
                os.close();
                os = null;
            }
        });
    }

    public InputStream decompress(InputStream in) throws Exception {
        return new BufferedInputStream(new InputStream() {
            ObjectInputStream is = new ObjectInputStream(in);
            NodeBase tree = ((NodeBase) is.readObject());
            Bos bos = new Bos();

            class Bos extends ByteArrayOutputStream {
                byte[] getBuf() {
                    return buf;
                }

                void setSize(int size) {
                    count = size;
                }
            }

            public int read() {
                throw new IllegalStateException();
            }

            public synchronized int read(byte[] buf, int off, int len) throws IOException {
                if (is == null)
                    return -1;

                int size;
                while (true) {
                    size = bos.size();
                    int dif = size - len;
                    if (dif >= 0) {
                        byte[] b = bos.getBuf();
                        System.arraycopy(b, 0, buf, off, len);
                        System.arraycopy(b, len, b, 0, dif);
                        bos.setSize(dif);
                        return len;
                    }
                    try {
                        Object o = is.readObject();
                        if (o instanceof NodeBase) {
                            tree = ((NodeBase) o);
                            o = is.readObject();
                        }
                        byte[] bytes = (byte[]) o;
                        decode(tree, bytes, 0, bytes.length, value -> bos.write(value));
                    } catch (ClassNotFoundException e) {
                        throw new IOException(e);
                    } catch (EOFException e) {
                        break;
                    }
                }
                System.arraycopy(bos.getBuf(), 0, buf, off, size);
                close();
                return size;
            }

            public synchronized void close() throws IOException {
                if (is == null)
                    return;
                is.close();
                is = null;
                tree.delete();
                tree = null;
                bos = null;
            }
        });
    }

    public static void encode(byte[] buf, int off, int len, HufConsumer c) {
        Leaf[] map = new Leaf[256];
        for (int i = 0; i < map.length; i++) {
            Leaf l = new Leaf();
            l.value = (byte) i;
            map[i] = l;
        }

        for (int i = 0; i < len; i++)
            map[buf[off + i] & 0xFF].count++;

        PriorityQueue<NodeBase> nodes = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));

        for (Leaf leaf : map)
            if (leaf.count != 0)
                nodes.offer(leaf);

        //build tree
        while (nodes.size() > 1)
            nodes.offer(new Node(nodes.poll(), nodes.remove()));
        NodeBase tree = nodes.remove();
        tree.setCode(0, (byte) 0);
        c.accept(tree);

        int cash = 0, bitLen = 0;
        for (int i = 0; i < len; i++) {
            Leaf leaf = map[buf[off + i] & 0xFF];
            cash |= (leaf.code & 0xFF) << bitLen;
            bitLen += leaf.len;
            while (bitLen >= 8) {
                c.accept(cash & 0xFF);
                cash >>>= 8;
                bitLen -= 8;
            }
        }
        //flush out buf
        if (bitLen > 0)
            c.accept(cash & 0xFF);
    }

    public static void decode(NodeBase tree, byte[] buf, int off, int len, IntConsumer c) {
        int pos = 0;
        int bitLen = 0;
        while (pos < len) {
            int key = 0;
            for (int m = off + pos, i = Math.min(off + len, m + 3) - 1; i >= m; i--)
                key = (key << 8) | (buf[i] & 0xFF);
            key >>>= bitLen;

            if (key == 0 && pos == len - 1)
                break;

            Leaf leaf = tree.find(key);
            bitLen += leaf.len;
            while (bitLen >= 8) {
                pos++;
                bitLen -= 8;
            }
            c.accept(leaf.value & 0xFF);
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1024; i++)
            sb.append(((char) i));
        String plainStr = sb.toString(); //"1234567890!@#$%^&*(ЙЦУКЕНГШЩёёкегнещ0";
        test(plainStr);
        //testStreams(plainStr);
    }

    private static void test(String plainStr) {
        byte[] plain = plainStr.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(plain.length);
        var consumer = new HufConsumer() {
            NodeBase tree;

            public void accept(NodeBase nodeBase) {
                tree = nodeBase;
            }

            public void accept(int value) {
                bos.write(value);
            }
        };
        encode(plain, 0, plain.length, consumer);
        byte[] compress = bos.toByteArray();
        bos.reset();
        decode(consumer.tree, compress, 0, compress.length, bos::write);
        String decompress = bos.toString(StandardCharsets.UTF_8);
        System.out.println(plainStr);
        System.out.println();
        System.out.println(new String(compress));
        System.out.println();
        System.out.println(decompress);
        System.out.println(plainStr.equals(decompress));
    }

    private static void testStreams(String plainStr) throws Exception {
        Huffman huffman = new Huffman();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(2048);
        OutputStream comOut = huffman.compress(bos);
        comOut.write(plainStr.getBytes(StandardCharsets.UTF_8));
        comOut.flush();

        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
        InputStream decIn = huffman.decompress(bin);

        String result = new String(decIn.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(plainStr);
        System.out.println(bos.toString());
        System.out.println(result);
        System.out.println(plainStr.equals(result));
    }
}