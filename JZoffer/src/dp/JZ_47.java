package dp;

public class JZ_47 {

    public static int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        if (grid.length <= 1) {
            int res = 0;
            for (int i = 0; i < grid[0].length; i++) res += grid[0][i];
            return res;
        }
        if (grid[0].length <= 1) {
            int res = 0;
            for (int i = 0; i < grid[0].length; i++) res += grid[i][0];
            return res;
        }

        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{new int[]{1,3,1}, new int[]{1,5,1}, new int[]{4,2,1}};
        System.out.println(maxValue(test));
    }
}
