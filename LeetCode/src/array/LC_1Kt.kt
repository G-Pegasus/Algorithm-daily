package array

fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return IntArray(0)
}

// 第一次只想暴力枚举，确实没想到还能哈希
fun twoSum2(nums: IntArray, target: Int): IntArray {
    val map: MutableMap<Int, Int> = HashMap()
    for (i in nums.indices) {
        if (map.containsKey(target - nums[i]))
            return intArrayOf(map[target - nums[i]]!!, i)

        map[nums[i]] = i
    }
    return IntArray(0)
}

fun main() {
    println(twoSum(intArrayOf(2, 7, 11, 15), 9).contentToString())
    println(twoSum2(intArrayOf(2, 7, 11, 15), 9).contentToString())
}