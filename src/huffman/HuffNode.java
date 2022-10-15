package huffman;


public class HuffNode {
    public Character word;
    public Integer value;
    public HuffNode lchild;
    public HuffNode rchild;
    public int isPapa;
    public String code;

    public HuffNode(Character word, Integer value) {
        this.value = value;
        this.word = word;
    }

    public HuffNode(HuffNode lchild, HuffNode rchild, Integer value, int isPapa) {

        this.lchild = lchild;
        this.rchild = rchild;
        this.value = value;
        this.isPapa = isPapa;
    }
}