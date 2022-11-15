package list;

import java.util.*;

public class LC_93 {

    public static List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>(4);

        if (s.length() < 4 || s.length() > 12) return list;

        dfs(s, s.length(), 0, 4, deque, list);
        return list;
    }

    // 判断该段数字是否合法
    private static boolean isJudge(String s, int left, int right) {
        int len = right - left + 1;
        int res = 0;

        if (len > 1 && s.charAt(left) == '0') return false;

        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }

    private static void dfs(String s, int len, int begin, int residue, Deque<String> deque, List<String> list) {
        if (begin == len && residue == 0) {
            list.add(String.join(".", deque));
            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) break;
            if (residue * 3 < len - i) continue;

            if (isJudge(s, begin, i)) {
                String currentSegment = s.substring(begin, i + 1);
                deque.offerLast(currentSegment);
                dfs(s, len, i + 1, residue - 1, deque, list);
                deque.pollLast();
            }
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> ans = restoreIpAddresses(s);

        for (String a : ans) {
            System.out.println(a);
        }
    }
}
