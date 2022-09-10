package string;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 滑动窗口问题
 * 窗口逐渐向右平移，如果和集合里面的元素不同就添加进去，相同就计算当前最大长度
 */
public class LC_3 {

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> maxString = new HashSet<>();
        int n = s.length();
        int right = -1, maxLength = 0;

        for (int i = 0; i < n; i++) {
            // 每次向右平移一位就将前一位移除集合
            if (i != 0) {
                maxString.remove(s.charAt(i - 1));
            }

            while (right + 1 < n && !maxString.contains(s.charAt(right + 1))) {
                maxString.add(s.charAt(right++ + 1));
            }

            maxLength = Math.max(maxLength, right - i + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(lengthOfLongestSubstring(s));
    }
}
