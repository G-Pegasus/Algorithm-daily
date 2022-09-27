package string;

public class LC_58 {

    public static int lengthOfLastWord(String s) {
        int length = 0;
        String s1 = " " + s;

        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) != ' ') {
                length++;
                if (s1.charAt(i - 1) == ' ') {
                    break;
                }
            }
        }

        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }
}
