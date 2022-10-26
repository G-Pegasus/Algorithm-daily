package sort;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        return sort(mergeSort(left), mergeSort(right));
    }

    private static int[] sort(int[] left, int[] right) {
        int[] ans = new int[left.length + right.length];
        int i = 0;

        while (left.length > 0 && right.length > 0) {
            if (left[0] < right[0]) {
                ans[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                ans[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            ans[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            ans[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{5, 8, 3, 1, 9, 3, 6})));
    }
}
