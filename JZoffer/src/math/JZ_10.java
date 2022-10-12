package math;

public class JZ_10 {

    public static int fib(int n) {
        long n0 = 0;
        long n1 = 1;
        long ans = 0;
        int i = n;

        while (i-- > 1) {
            ans = (n0 + n1) % 1000000007;
            n0 = n1;
            n1 = ans;
        }

        if (n == 1) return 1;
        return (int) ans;
    }

    public static int numWays(int n) {
        int n0 = 1;
        long n1 = 1;
        long n2 = 2;
        long ans = 0;
        int i = n;

        while (i-- > 2) {
            ans = (n1 + n2) % 1000000007;
            n1 = n2;
            n2 = ans;
        }

        if (n == 0) return n0;
        else if (n == 1) return 1;
        else if (n == 2) return 2;
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(fib(95));
        System.out.println(numWays(3));
    }
}
