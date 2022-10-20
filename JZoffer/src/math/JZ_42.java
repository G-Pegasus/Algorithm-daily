package math;

import java.util.Arrays;
import java.util.HashSet;

public class JZ_42 {

    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        HashSet<Integer> set = new HashSet<>();
        set.contains(2);

        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
        }
        Arrays.sort(dp);
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
