package stack;

import java.util.ArrayList;

public class JZ_30 {

    ArrayList<Integer> array1;
    ArrayList<Integer> array2;

    public JZ_30() {
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        array2.add(Integer.MAX_VALUE);
    }

    public void push(int x) {
        array1.add(x);
        array2.add(Math.min(array2.get(array2.size() - 1), x));
    }

    public void pop() {
        array1.remove(array1.size() - 1);
        array2.remove(array2.size() - 1);
    }

    public int top() {
        return array1.get(array1.size() - 1);
    }

    public int min() {
        return array2.get(array2.size() - 1);
    }
}
