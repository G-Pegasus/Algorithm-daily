package stack;

import java.util.ArrayList;

public class JZ_58 {

    public static String reverseWords(String s) {
        if (s.equals("")) return "";
        int left = 0, right = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        String s1 = s + " ";

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') {
                if (!s1.substring(left, right).equals("")) {
                    arrayList.add(s1.substring(left, right));
                }
                left = right;
                left++;
            }
            right++;
        }

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            stringBuilder.append(arrayList.get(i));
            stringBuilder.append(" ");
            arrayList.remove(i);
        }

        if (stringBuilder.length() > 1)
            return stringBuilder.substring(0, stringBuilder.toString().length() - 1);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(" "));
    }
}
