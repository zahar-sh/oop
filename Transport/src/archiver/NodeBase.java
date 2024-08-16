package archiver;

import java.io.Serializable;

abstract class NodeBase implements Serializable {
    int count;

    abstract void setLeafCode(int code, byte l);
    abstract Leaf find(int code);
    abstract void delete();
}