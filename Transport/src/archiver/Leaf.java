package archiver;

class Leaf extends NodeBase {
    byte value;
    int code;
    byte len;

    void setLeafCode(int code, byte len) {
        this.code = code;
        this.len = len;
    }
    Leaf find(int code) {
        return this;
    }
    void delete() {
    }

    @Override
    public String toString() {
        String codeStr = Integer.toBinaryString(code);
        int t = len - codeStr.length();
        codeStr = t > 0 ? ("0".repeat(t) + codeStr) : codeStr.substring(-t);
        return String.format("[%d, %d, %s, %d]", value, count, codeStr, len);
    }
}
