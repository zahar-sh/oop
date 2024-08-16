package archiver.huffman;

import java.io.Serializable;

abstract class NodeBase implements Serializable {
    int count;

    abstract void setCode(int code, byte l);
    abstract Leaf find(int code);
    abstract void delete();
}