package string;

public class LC_5 {

    public static String longestPalindrome(String s) {
        // 暴力破解失败
//        String ret = "";
//
//        if (s.length() == 1)
//            return s;
//
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = s.length() - 1; j > i; j--) {
//                int length = 0, ii = i, jj = j;
//                //System.out.println(s.charAt(ii) + " " + s.charAt(jj));
//                while (s.charAt(ii) == s.charAt(jj) && i != jj) {
//                    ii++; jj--;
//                    length++;
//                    //System.out.println("length = " + length);
//                }
//
//                if (length > ret.length())
//                    ret = s.substring(i, ii + 1);
//            }
//        }
//
//        if (ret.equals("")) return s.substring(0, 1);
//        else return ret;
        int len = s.length();
        if (len < 2) return s;

        int maxLen = 1, begin = 0;
        // 该dp用于判断 s[i..j]是否为回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        // 通过该循环使边界长度逐渐扩大
        for (int length = 2; length <= len; length++) {
            for (int i = 0; i < len; i++) {
                int j = length + i - 1;
                if (j >= len) break; // 越界则退出循环

                // 如果最外层都不相等，就不用往里面收缩了
                if (charArray[i] != charArray[j]) dp[i][j] = false;
                else {
                    if (j - i < 3) dp[i][j] = true;
                    // 逐渐向内收缩
                    else dp[i][j] = dp[i + 1][j - 1];
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String s = "ac";
        System.out.println(longestPalindrome(s));
    }
}
