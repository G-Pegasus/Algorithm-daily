package math

fun reverse(x: Int): Int {
    var xx = x
    var ret = 0
    while (x != 0) {
        // 每次都要判断得出的 ret 是否在题干范围值中
        if (ret < Int.MIN_VALUE / 10 || ret > Int.MAX_VALUE / 10) return 0
        val digit = xx % 10
        xx /= 10
        ret = ret * 10 + digit
    }
    return ret
}