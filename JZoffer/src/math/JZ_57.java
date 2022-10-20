package math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JZ_57 {

    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        int[] ans = new int[2];

        for (int num : nums) {
            set.add(num);
            if (set.contains(target - num)) {
                ans[0] = num;
                ans[1] = target - num;
            }
        }

        return ans;
    }

    public int[][] findContinuousSequence(int target) {
        int start = 1, end = 2, sum = 3;
        ArrayList<int[]> ans = new ArrayList<>();

        while (start < end) {
            if (sum == target) {
                int[] res = new int[end - start + 1];
                for (int i = start; i <= end; i++) {
                    res[i - start] = i;
                }
                ans.add(res);
            }

            if (sum >= target) {
                sum -= start;
                start--;
            } else {
                end++;
                sum += end;
            }
        }

        return ans.toArray(new int[0][]);
    }
}
