package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static int[] quickSort(int[] nums) {
        return sort(nums, 0, nums.length - 1);
    }

    public static int[] sort(int[] nums, int left, int right) {
        if (left < right) {
            int index = randomPartition(nums, left, right);
            sort(nums, left, index - 1);
            sort(nums, index + 1, right);
        }
        return nums;
    }

    public static int randomPartition(int[] nums, int left, int right) {
        int index = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, index);
        return partition(nums, left, right);
    }

    public static int partition(int[] nums, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[left]) {
                swap(nums, i, index++);
            }
        }
        swap(nums, left, index - 1);
        return index - 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(quickSort(new int[]{5, 8, 3, 1, 9, 3, 6})));
    }
}
