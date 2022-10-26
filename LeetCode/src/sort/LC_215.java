package sort;

import java.util.Random;

public class LC_215 {

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    static int quickSelect(int[] arr, int left, int right, int index) {
        int q = randomPartition(arr, left, right);
        if (q == index) {
            return arr[q];
        } else {
            return q < index ? quickSelect(arr, q + 1, right, index) : quickSelect(arr, left, q - 1, index);
        }
    }

    static int randomPartition(int[] arr, int left, int right) {
        int index = new Random().nextInt(right - left + 1) + left;
        swap(arr, right, index);
        return partition(arr, left, right);
    }

    static int partition(int[] arr, int left, int right) {
        int index = left, x = arr[right];
        // 有比最右边小的，都放在左边
        for (int i = left; i < right; i++) {
            if (arr[i] <= x) {
                swap(arr, i, index++);
            }
        }
        // 最后，把最右边的和index交换，这样在index左边的都是比它小的
        swap(arr, right, index);
        return index;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
}
