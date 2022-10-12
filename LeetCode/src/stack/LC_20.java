package stack;

import java.util.Stack;

public class LC_20 {

    public static boolean isValid(String s) {
        Stack<Character> characters = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                characters.push(s.charAt(i));
            } else if (s.charAt(i) == '}') {
                if (characters.isEmpty()) return false;
                if (characters.peek() == '{') {
                    characters.pop();
                } else {
                    return false;
                }
            } else if (s.charAt(i) == ']') {
                if (characters.isEmpty()) return false;
                if (characters.peek() == '[') {
                    characters.pop();
                } else {
                    return false;
                }
            } else if (s.charAt(i) == ')') {
                if (characters.isEmpty()) return false;
                if (characters.peek() == '(') {
                    characters.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return characters.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[()()[]]}"));
    }
}
