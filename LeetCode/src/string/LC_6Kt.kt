package string

fun convert(s: String, numRows: Int): String {
    val len = s.length
    if (numRows == 1 || numRows >= len) return s

    // 计算矩阵有多少列
    val t = 2 * numRows - 2
    val c = (len + t - 1) / t * (numRows - 1)
    val chars = Array(numRows) { CharArray(c) }
    var i = 0
    var x = 0
    var y = 0
    while (i < len) {
        chars[x][y] = s[i]
        if (i % t < numRows - 1) {
            x++ // 向下移动
        } else {
            y++
            x-- // 向右上移动
        }
        i++
    }
    val ret = StringBuilder()
    for (j in 0 until numRows) {
        for (i in 0 until c) {
            if (chars[j][i] != ' ') ret.append(chars[j][i])
        }
    }
    return ret.toString()
}