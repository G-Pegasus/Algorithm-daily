package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class JZ_59 {

    public static int[] maxSlidingWindowMe(int[] nums, int k) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        ans[0] = max;

        for (int i = k; i < nums.length; i++) {
            queue.offer(nums[k]);
            queue.poll();
            max = Integer.MIN_VALUE;
            for (int j = i - k + 1; j <= i; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            ans[i - k + 1] = max;
        }

        return ans;
    }

    // 单调队列，维持队列内一直是递减的状态，这样队列的头部就是当前窗口的最大值
    @SuppressWarnings("ConstantConditions")
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];

        // 形成窗口前
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
                queue.pollLast();
            }
            queue.offerLast(nums[i]);
        }
        ans[0] = queue.peekFirst();

        // 形成窗口后
        for (int i = k; i < nums.length; i++) {
            if (queue.peekFirst() == nums[i - k]) queue.pollFirst();
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
                queue.pollLast();
            }
            queue.offerLast(nums[i]);
            ans[i - k + 1] = queue.peekFirst();
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3)));
    }
}
