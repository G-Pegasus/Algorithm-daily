package string

fun longestPalindrome(s: String): String {

    val len = s.length
    if (len < 2) return s
    var maxLen = 1
    var begin = 0
    // 该dp用于判断 s[i..j]是否为回文串
    val dp = Array(len) { BooleanArray(len) }
    val charArray = s.toCharArray()
    for (i in 0 until len) {
        dp[i][i] = true
    }

    // 通过该循环使边界长度逐渐扩大
    for (length in 2..len) {
        for (i in 0 until len) {
            val j = length + i - 1
            if (j >= len) break // 越界则退出循环

            // 如果最外层都不相等，就不用往里面收缩了
            if (charArray[i] != charArray[j]) dp[i][j] = false else {
                if (j - i < 3) dp[i][j] = true else dp[i][j] = dp[i + 1][j - 1]
            }
            if (dp[i][j] && j - i + 1 > maxLen) {
                maxLen = j - i + 1
                begin = i
            }
        }
    }
    return s.substring(begin, begin + maxLen)
}