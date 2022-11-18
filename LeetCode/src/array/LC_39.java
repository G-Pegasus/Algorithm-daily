package array;

import java.util.ArrayList;
import java.util.List;

public class LC_39 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backTrack(ans, list, candidates, 0, target);
        return ans;
    }

    static void backTrack(List<List<Integer>> ans, List<Integer> list, int[] nums, int begin, int target) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            list.add(nums[i]);
            if (target - nums[i] >= 0) {
                backTrack(ans, list, nums, i, target - nums[i]);
            }
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,5}, 8));
    }
}
