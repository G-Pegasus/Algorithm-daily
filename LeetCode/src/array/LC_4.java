package array;

import java.util.Arrays;

public class LC_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int length1 = nums1.length;
//        int length2 = nums2.length;
//        int[] nums = new int[length1 + length2];
//        double ret;
//
//        System.arraycopy(nums1, 0, nums, 0, length1);
//        System.arraycopy(nums2, 0, nums, length1, length2);
//        Arrays.sort(nums);
//
//        if (nums.length % 2 == 0) {
//            ret = (double) (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2;
//        } else {
//            ret = nums[nums.length / 2];
//        }
//
//        return ret;


        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            // System.out.println(i);
            // System.out.println(j);

            int nums1Max = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums1Min = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums2Max = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums2Min = (j == n ? Integer.MAX_VALUE : nums2[j]);

            // System.out.println("nums1Max = " + nums1Max + " nums2Min = " + nums2Min);
            if (nums1Max <= nums2Min) {
                median1 = Math.max(nums1Max, nums2Max);
                // System.out.println("median1 = " + median1);
                median2 = Math.min(nums1Min, nums2Min);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2,4,7,8};
        int[] nums2 = new int[]{5,6,9,12,17};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
