package huffman;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(set.size());
        List<Integer> list = new ArrayList<>();

        List<List<Integer>> list1 = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        res1.add(2);
        res1.add(1);
        res1.sort(Comparator.comparingInt(o -> o));
        res2.add(1);
        res2.add(2);
        list1.add(res1);
        System.out.println(list1.contains(res2));
    }
}

