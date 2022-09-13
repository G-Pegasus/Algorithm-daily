package string;

/** 力扣第六题，构造二维数组，将 Z 字形字符串填入进去，然后横着遍历即可
 *  但较为浪费内存
 */
public class LC_6 {

    public static String convert(String s, int numRows) {
        int len = s.length();

        if (numRows == 1 || numRows >= len) return s;

        // 计算矩阵有多少列
        int t = 2 * numRows - 2;
        int c = (len + t - 1) / t * (numRows - 1);
        char[][] chars = new char[numRows][c];

        for (int i = 0, x = 0, y = 0; i < len; i++) {
            chars[x][y] = s.charAt(i);
            if (i % t < numRows - 1) {
                x++; // 向下移动
            } else {
                y++; x--; // 向右上移动
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < c; i++) {
                if (chars[j][i] != 0)
                    ret.append(chars[j][i]);
            }
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PA", 1));
    }
}
