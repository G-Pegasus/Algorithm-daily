package math;

import java.util.Arrays;

public class JZ_40 {

    public static int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];

        System.arraycopy(arr, 0, ans, 0, k);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{0,0,1,2,4,2,2,3,1,4}, 8)));
    }
}
