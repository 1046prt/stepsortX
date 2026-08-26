// Stepsort · Minimum Path Sum
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-path-sum

public class Main {
    // dp[j] = min path sum to reach cell in current row, column j
    static int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] dp = new int[cols];
        dp[0] = grid[0][0];
        for (int j = 1; j < cols; j++) dp[j] = dp[j - 1] + grid[0][j];
        for (int i = 1; i < rows; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < cols; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1},
        };
        System.out.println("Minimum path sum: " + minPathSum(grid));
    }
}
