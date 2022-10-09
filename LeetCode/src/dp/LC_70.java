package dp;

public class LC_70 {

    public static int climbStairs(int n) {
        int p, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
}
