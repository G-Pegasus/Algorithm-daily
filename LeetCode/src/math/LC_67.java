package math;

public class LC_67 {

    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();

        int temp = 0, maxLength = Math.max(a.length(), b.length());

        for (int i = 0; i < maxLength; i++) {
            temp += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            temp += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (temp % 2 + '0'));
            temp /= 2;
        }

        // 如果得到的进位 temp 大于0，就在最前方补个1
        if (temp > 0) ans.append('1');

        // 将得到的字符串反转
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }
}
