// Stepsort · Unique Paths
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/unique-paths

public class Main {
    // dp[j] holds ways to reach column j while sweeping rows top to bottom
    static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) dp[j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Unique paths in a 3 x 7 grid: " + uniquePaths(3, 7));
    }
}
