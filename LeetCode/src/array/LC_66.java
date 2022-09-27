package array;

import java.util.Arrays;

public class LC_66 {

    public static int[] plusOne(int[] digits) {
        // 结尾不是9的情况
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1]++;
            return digits;
        }

        // 数组中的数字全部是9的情况
        int count = 0;
        for (int digit : digits) {
            if (digit == 9) {
                count++;
            }
        }
        if (count == digits.length) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }

        // 尾部是9但头不是9的情况
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                break;
            }
        }

        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9})));
    }
}
