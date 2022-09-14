package math;

public class LC_7 {

    public static int reverse(int x) {
        int ret = 0;

        while (x != 0) {
            // 每次都要判断得出的 ret 是否在题干范围值中
            if (ret < Integer.MIN_VALUE / 10 || ret > Integer.MAX_VALUE / 10)
                return 0;

            int digit = x % 10;
            x /= 10;
            ret = ret * 10 + digit;
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
