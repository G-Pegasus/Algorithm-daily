package math;

public class LC_13 {

    public static int romanToInt(String s) {
        int total = 0;
        String s1 = s + "a";
        for (int i = 0; i < s1.length() - 1; i++) {
            if (s1.charAt(i) == 'I') {
                if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'V') {
                    total += 4;
                    i++;
                } else if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'X') {
                    total += 9;
                    i++;
                } else {
                    total += 1;
                }
            } else if (s1.charAt(i) == 'V') {
                total += 5;
            } else if (s1.charAt(i) == 'X') {
                if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'L') {
                    total += 40;
                    i++;
                } else if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'C') {
                    total += 90;
                    i++;
                } else {
                    total += 10;
                }
            } else if (s1.charAt(i) == 'L') {
                total += 50;
            } else if (s1.charAt(i) == 'C') {
                if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'D') {
                    total += 400;
                    i++;
                } else if (s1.charAt(i + 1) != 'a' && s1.charAt(i + 1) == 'M') {
                    total += 900;
                    i++;
                } else {
                    total += 100;
                }
            } else if (s1.charAt(i) == 'D') {
                total += 500;
            } else if (s1.charAt(i) == 'M') {
                total += 1000;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("XX"));
    }
}
