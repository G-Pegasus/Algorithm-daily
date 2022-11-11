package list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LC_146 {

    Map<Integer, Integer> map;
    Deque<Integer> deque;
    int capacity;

    public LC_146(int capacity) {
        map = new HashMap<>();
        deque = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            deque.remove(key);
            deque.offerLast(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.replace(key, value);
            deque.remove(key);
            deque.offerLast(key);
        } else {
            if (deque.size() < capacity) {
                map.put(key, value);
                deque.offerLast(key);
            } else {
                int key1 = deque.pollFirst();
                deque.offerLast(key);
                map.remove(key1);
                map.put(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LC_146 test = new LC_146(2);
        test.put(1, 1);
        test.put(2, 2);
        System.out.println(test.get(1));
        test.put(3, 3);
        System.out.println(test.get(2));
        test.put(4, 4);
        System.out.println(test.get(1));
        System.out.println(test.get(3));
        System.out.println(test.get(4));
    }
}
