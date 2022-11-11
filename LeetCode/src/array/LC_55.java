package array;

public class LC_55 {

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(i + nums[i], maxLen);
            if (i >= maxLen && i != n - 1) return false;
            if (maxLen == n - 1) return true;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,0,0}));
    }
}
