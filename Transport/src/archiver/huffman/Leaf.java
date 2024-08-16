package archiver.huffman;

class Leaf extends NodeBase {
    byte value;
    int code;
    byte len;

    void setCode(int code, byte len) {
        this.code = code;
        this.len = len;
    }
    Leaf find(int c) {
        return this;//(c & code) == code ? this : null;
    }
    void delete() {
    }

    public String toString() {
        String codeStr = Integer.toBinaryString(code);
        int t = len - codeStr.length();
        codeStr = t > 0 ? ("0".repeat(t) + codeStr) : codeStr.substring(-t);
        return String.format("[%d, %d, %s, %d]", value, count, codeStr, len);
    }
}