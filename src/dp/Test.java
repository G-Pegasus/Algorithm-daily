package dp;

import java.util.Arrays;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {

        String s = "1.2.1";
        String[] str = s.split("\\.");
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        builder.append('a');
        builder.substring(0, builder.toString().length() - 1);
        System.out.println(Integer.parseInt("001"));
        System.out.println(Arrays.toString(str));
    }
}