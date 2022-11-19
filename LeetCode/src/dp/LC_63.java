package dp;

public class LC_63 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length, c = obstacleGrid[0].length;
        if (obstacleGrid[r - 1][c - 1] == 1 || obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[r][c];
        dp[0][0] = 1;

        f1: for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 && j > 0) {
                    if (obstacleGrid[i][j] == 1) {
                        break f1;
                    } else {
                        dp[0][j] = 1;
                    }
                }
            }
        }

        f2: for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i > 0 && j == 0) {
                    if (obstacleGrid[i][0] == 1) {
                        break f2;
                    } else {
                        dp[i][0] = 1;
                    }
                }
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (obstacleGrid[i - 1][j] != 1 && obstacleGrid[i][j - 1] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] != 1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (obstacleGrid[i - 1][j] != 1 && obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[r - 1][c - 1];
    }
}
