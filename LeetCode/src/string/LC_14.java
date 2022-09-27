package string;

import java.util.Objects;

public class LC_14 {

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 1) return strs[0];

        int min = 201;
        for (String str : strs) {
            if (Objects.equals(str, "")) return "";
            if (str.length() < min) {
                min = str.length();
            }
        }

        int count = 1;
        System.out.println(min);
        w:for (int i = 0; i < min; i++) {
            for (int j = 0; j < strs.length - 1; j++) {

                if (strs[j].charAt(i) == strs[j + 1].charAt(i)) {
                    count = i + 1;
                } else {
                    count = i;
                    break w;
                }
            }
        }
        return strs[0].substring(0, count);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abbb","a","accc","aa"}));
    }
}
