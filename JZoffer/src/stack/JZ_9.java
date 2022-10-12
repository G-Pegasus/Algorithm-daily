package stack;

import java.util.Stack;

public class JZ_9 {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public JZ_9() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        s1.push(value);
    }

    public int deleteHead() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.isEmpty() ? -1 : s2.pop();
    }
}
