package string

fun lengthOfLongestSubstring(s: String): Int {
    val maxString: MutableSet<Char> = HashSet()
    val n = s.length
    var right = -1
    var maxLength = 0
    for (i in 0 until n) {

        if (i != 0) {
            maxString.remove(s[i - 1])
        }

        while (right + 1 < n && !maxString.contains(s[right + 1])) {
            maxString.add(s[right++ + 1])
        }

        maxLength = maxLength.coerceAtLeast(right - i + 1)
    }
    return maxLength
}
