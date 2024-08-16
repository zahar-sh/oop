package archiver;


import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class JavaArchiver implements Archiver {
    private static class InstanceHolder {
        static final JavaArchiver INSTANCE = new JavaArchiver();
    }
    public static JavaArchiver getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private JavaArchiver() {
    }

    public OutputStream compress(OutputStream out) {
        ZipOutputStream os = new ZipOutputStream(out);
        return new BufferedOutputStream(new OutputStream() {
            public void write(int b) {
                throw new IllegalStateException();
            }

            public void write(byte[] b, int off, int len) throws IOException {
                ZipEntry z = new ZipEntry("z");
                z.setExtra(Arrays.copyOfRange(b, off, off + len));
                os.putNextEntry(z);
            }

            public void flush() throws IOException {
                os.flush();
            }

            public void close() throws IOException {
                os.close();
            }
        });
    }
    public InputStream decompress(InputStream in) {
        ZipInputStream is = new ZipInputStream(in);
        return new BufferedInputStream(new InputStream() {
            public int read() {
                throw new IllegalStateException();
            }

            public int read(byte[] b, int off, int len) throws IOException {
                ZipEntry entry = is.getNextEntry();
                if (entry == null)
                    return -1;
                byte[] extra = entry.getExtra();
                if (len > extra.length)
                    len = extra.length;
                System.arraycopy(extra, 0, b, off, len);
                return len;
            }

            public void close() throws IOException {
                is.close();
            }
        });
    }
}
