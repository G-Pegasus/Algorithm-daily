package array

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
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
    if (nums1.size > nums2.size) {
        return findMedianSortedArrays(nums2, nums1)
    }
    val m = nums1.size
    val n = nums2.size
    var left = 0
    var right = m
    // median1：前一部分的最大值
    // median2：后一部分的最小值
    var median1 = 0
    var median2 = 0
    while (left <= right) {
        // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
        // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
        val i = (left + right) / 2
        val j = (m + n + 1) / 2 - i

        // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
        val nums_im1 = if (i == 0) Int.MIN_VALUE else nums1[i - 1]
        val nums_i = if (i == m) Int.MAX_VALUE else nums1[i]
        val nums_jm1 = if (j == 0) Int.MIN_VALUE else nums2[j - 1]
        val nums_j = if (j == n) Int.MAX_VALUE else nums2[j]
        if (nums_im1 <= nums_j) {
            median1 = nums_im1.coerceAtLeast(nums_jm1)
            median2 = nums_i.coerceAtMost(nums_j)
            left = i + 1
        } else {
            right = i - 1
        }
    }
    return if ((m + n) % 2 == 0) (median1 + median2) / 2.0 else median1.toDouble()
}