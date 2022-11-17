package list;

import java.util.*;

public class LC_347 {

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) return nums;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[k];

        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int t = map.get(key);
            if (list[t] == null) list[t] = new ArrayList<>();
            list[t].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) continue;
            res.addAll(list[i]);
        }

        for (int i = 0; i < k; i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{-1, -1}, 1)));
    }
}
