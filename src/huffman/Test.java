package huffman;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] f = new int[n];

        for (int i = 0; i < f.length; i++) {
            f[i] = scanner.nextInt();
        }
        String max = "";

        for (int i = 0; i < f.length - 1; i++) {
            for (int j = i + 1; j < f.length; j++) {
                if (!max(Long.parseLong(f[i] + "" + f[j]), Long.parseLong(f[j] + "" + f[i]))) {
                    int temp = f[i];
                    f[i] = f[j];
                    f[j] = temp;
                }
            }
        }

        for (int j : f) {
            max = max.concat(j + "");
        }

        System.out.println(max);
    }

    private static boolean max(Long a, Long b) {
        return a > b;
    }
}

