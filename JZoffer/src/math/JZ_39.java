package math;

import java.util.Arrays;

public class JZ_39 {

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int x = nums[0], count = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == x) {
                count++;
            } else {
                x = nums[i];
                count = 1;
            }

            if (count > (n / 2)) {
                return nums[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2}));
    }
}
