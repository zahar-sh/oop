package archiver;

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
    void setLeafCode(int code, byte l) {
        int newCode = code | (1 << l);
        l++;
        left.setLeafCode(newCode, l);
        right.setLeafCode(code, l);
    }
    void delete() {
        left.delete();
        right.delete();
        left = right = null;
    }
}
