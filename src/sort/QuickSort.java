package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static int[] quickSort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    private static int[] sort(int[] arr, int left, int right) {
        if (left < right) {
            int index = randomizedPartition(arr, left, right);
            sort(arr, left, index - 1);
            sort(arr, index + 1, right);
        }
        return arr;
    }

    // 随机选取一个基准
    private static int randomizedPartition(int[] nums, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, i);
        return partition(nums, left, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(arr, index, i);
                index++;
            }
        }
        swap(arr, left, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(quickSort(new int[]{5, 8, 3, 1, 9, 3, 6})));
    }
}
