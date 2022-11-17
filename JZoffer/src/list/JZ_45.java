package list;

import java.util.*;

public class JZ_45 {

    public String minNumber(int[] nums) {
        int len = nums.length;
        String[] strings = new String[len];

        for (int i = 0; i < len; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(strings[i]);
        }

        return builder.toString();
    }
}
