package dp;

public class JZ_46 {

    public int translateNum(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        int a = 1, b = 1;

        for (int i = 2; i <= n; i++) {
            int tmp = Integer.parseInt(s.substring(i - 2, i));
            int c = tmp >= 10 && tmp <= 25 ? a + b : b;
            a = b;
            b = c;
        }

        return b;
    }
}
