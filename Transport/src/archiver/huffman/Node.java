package archiver.huffman;

class Node extends NodeBase {
    NodeBase left, right;

    Node(NodeBase left, NodeBase right) {
        this.count = (left.count + right.count);
        this.left = left;
        this.right = right;
    }

    Leaf find(int code) {
        NodeBase n = (code & 1) == 0 ? right : left;
        return n.find(code >>> 1);
    }
    void setCode(int code, byte l) {
        int newCode = code | (1 << l);
        l++;
        left.setCode(newCode, l);
        right.setCode(code, l);
    }
    void delete() {
        left.delete();
        right.delete();
        left = right = null;
    }
}