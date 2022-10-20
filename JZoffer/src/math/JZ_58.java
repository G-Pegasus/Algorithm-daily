package math;

import java.util.ArrayList;

public class JZ_58 {

    public String reverseLeftWords(String s, int n) {
        String str = s.substring(0, n);
        ArrayList<Integer> arr = new ArrayList<>();
        return s.substring(n + 1) + str;

    }
}
