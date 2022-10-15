package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HuffTree {

    public HuffNode root;

    /*
     * 计算字符串中各字出现的次数
     */
    public void culate(String s, Map<Character, Integer> board) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (board.containsKey(c)) {        // 检验map中是否存在当前读取的字符
                Integer count = board.get(c);
                count += 1;
                board.put(c, count);
            } else {
                board.put(c, 1);
            }
        }
    }

    /*
     * 对哈希表进行排序
     */
    public void hashSort(List<Map.Entry<Character, Integer>> list) {
        list.sort(Map.Entry.comparingByValue());
    }

    /*
     * *创建哈夫曼树
     */
    public HuffNode create(List<Map.Entry<Character, Integer>> list) {
        ArrayList<HuffNode> nodes = new ArrayList<>();

        for (Map.Entry<Character, Integer> characterIntegerEntry : list) {
            nodes.add(new HuffNode(characterIntegerEntry.getKey(), characterIntegerEntry.getValue()));        //建立结点类表，根据排序表，从哈希表中添加节点
        }

        while (nodes.size() > 1) {
            list.sort(Map.Entry.comparingByValue());    //排序

            HuffNode l = nodes.get(0);        //取出前两个结点即最小的两个结点
            HuffNode r = nodes.get(1);

            r.code = "0";                    //赋予编码的初值
            l.code = "1";
            transToCode(r);                    //继续编码
            transToCode(l);
            HuffNode p = new HuffNode(l, r, l.value + r.value, -1);        //产生两个权值相加形成的结点

            nodes.remove(0);    //因前两个结点已加入哈夫曼树，故删去
            nodes.remove(0);

            nodes.add(p);        //将产生新的相加权值结点添加到结点类表
        }
        root = nodes.get(0);
        return nodes.get(0);

    }

    /*
     * *前根遍历 -> 输出编码表
     */
    public void display(HuffNode T) {
        if (T != null) {
            if (T.isPapa != -1)            //isPaPa 为-1的都是后来形成的结点，不具有编码意义，
                System.out.print(T.word + "   " + T.code + "   ");
            display(T.lchild);
            display(T.rchild);
        }
    }

    /*
     * 对哈夫曼树进行编码
     */
    public void transToCode(HuffNode T) {
        if (T.lchild != null) {            // 遍历哈夫曼树，根据左0右1原则添加编码
            T.lchild.code = T.code + "0";
            transToCode(T.lchild);
        }
        if (T.rchild != null) {
            T.rchild.code = T.code + "1";
            transToCode(T.rchild);
        }
    }

    /*
     * 将给定字符串编码
     */
    String hfmCode = "";    // 储存编码完成后的字符串

    public void search(HuffNode T, Character str) {
        if (T.lchild == null && T.rchild == null) {
            if (str.equals(T.word)) {    // 检验该值是否与哈夫曼树中的值（word）匹配
                hfmCode += T.code;
            }
        }
        if (T.lchild != null) {            // 遍历哈夫曼树匹配相应的编码
            search(T.lchild, str);
        }
        if (T.rchild != null) {
            search(T.rchild, str);
        }
    }

    public String toHuffmanCode(String str) {

        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);        // 逐字匹配
            search(root, c);
        }

        return hfmCode;                    // 返回编码完成后的字符串
    }

    /*
     * 对哈夫曼树进行解码
     */
    String result = "";            // 储存解码后的字符
    boolean target = false;        // 标志匹配是否成功

    public void match(HuffNode T, String code) {
        if (T.lchild == null && T.rchild == null) {
            if (code.equals(T.code)) {            // 检验该值是否与哈夫曼树中的编码匹配
                result += T.word;
                target = true;
            }
        }
        if (T.lchild != null) {        // 遍历哈夫曼树，匹配相应的值
            match(T.lchild, code);
        }
        if (T.rchild != null) {
            match(T.rchild, code);
        }
    }

    public String CodeToWord(String codeStr) {
        int start = 0;
        int end = 1;
        while (end <= codeStr.length()) {
            target = false;
            String s = codeStr.substring(start, end);            // 切割然后开始匹配
            match(root, s);
            if (target) {            // 说明匹配成功
                start = end;        // 改变切割开始的位置，因为前面已经匹配完成
            }
            end++;
        }
        return result;            // 返回解码完成后的字符串
    }

    public static void main(String[] args) {
        HuffTree a = new HuffTree();
        Map<Character, Integer> board = new HashMap<>();

        System.out.println("请输入字符串：");
        Scanner sc = new Scanner(System.in);
        String inputString = sc.next();
        sc.close();
        a.culate(inputString, board);            // 计算字符串中个字符出现的频率
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(board.entrySet());
        a.hashSort(list);            /// 对频率表进行排序
        HuffNode root1 = a.create(list);        // 建立哈夫曼树
        System.out.println("编码表如下：");
        a.display(root1);                    // 输出编码表
        System.out.println();
        System.out.println("编码后：");
        String cc = a.toHuffmanCode(inputString);            // 将数输入的字符串编码
        System.out.println(cc);                    // 输出编码
        System.out.println("解码后：");
        System.out.println(a.CodeToWord(cc) + "");    // 将编码解码后输出
    }
}