package string;

public class LC_767 {

    public static String reorganizeString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        reset(n, chars);
        if (!isValid(chars)) {
            boolean flag = isValid(reverse(chars));
            if (flag) return String.valueOf(reverse(chars));
            else return "";
        }

        return String.valueOf(chars);
    }

    static char[] reverse(char[] chars) {
        int n = chars.length;
        char[] chars1 = new char[chars.length];
        for (int i = chars.length - 1; i >= 0; i--){
            chars1[chars.length - i - 1] = chars[i];
        }

        reset(n, chars1);
        return chars1;
    }

    static void reset(int n, char[] chars1) {
        for (int i = 0; i < n - 1; i++) {
            if (chars1[i + 1] == chars1[i]) {
                for (int j = i + 2; j < n; j++) {
                    if (chars1[i + 1] != chars1[j]) {
                        char temp;
                        temp = chars1[i + 1];
                        chars1[i + 1] = chars1[j];
                        chars1[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    static boolean isValid(char[] chars) {
        int n = chars.length;

        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("vvvlo"));
    }
}
