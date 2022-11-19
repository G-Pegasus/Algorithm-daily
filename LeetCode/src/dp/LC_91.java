package dp;

public class LC_91 {

    public static int numDecodings(String s) {
        int n = s.length();

        if (s.charAt(0) == '0') return 0;

        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; i++) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }

            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0' <= 26)) {
                c += a;
            }

            a = b;
            b = c;
        }

        return c;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }
}
