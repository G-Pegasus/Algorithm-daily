package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC_46 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        dfs(ans, list, n, 0);
        return ans;
    }

    static void dfs(List<List<Integer>> ans, List<Integer> list, int n, int first) {
        if (first == n) {
            ans.add(new ArrayList<>(list));
        } else {
            for (int i = first; i < n; i++) {
                Collections.swap(list, first, i);
                dfs(ans, list, n, first + 1);
                Collections.swap(list, first, i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
