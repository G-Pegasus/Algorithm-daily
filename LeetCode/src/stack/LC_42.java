package stack;

import java.util.Stack;

public class LC_42 {

    // 双指针的方法，按列计算，取左右两边最小列（木桶效应）
    public static int trap(int[] height) {
        int ans = 0;
        int left = 1, right = height.length - 2;
        int left_max = 0, right_max = 0;

        for (int i = 1; i < height.length - 1; i++) {
            if (height[left - 1] < height[right + 1]) {
                left_max = Math.max(height[left - 1], left_max);
                int temp = left_max;
                if (temp > height[left]) {
                    ans += temp - height[left];
                }
                left++;
            } else {
                right_max = Math.max(height[right + 1], right_max);
                int temp = right_max;
                if (temp > height[right]) {
                    ans += temp - height[right];
                }
                right--;
            }
        }

        return ans;
    }


    public static int trap1(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        int ans = 0;

        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.pop()];
                if (stack.isEmpty())
                    break;
                int dis = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                ans += dis * (min - h);
            }
            stack.push(current);
            current++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(trap1(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
    }
}
