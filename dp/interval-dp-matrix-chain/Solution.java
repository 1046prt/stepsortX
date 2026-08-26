// sortsort · Interval DP (Matrix Chain)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-dp-matrix-chain

public class Main {
    public static void main(String[] args) {
        int[] dims = {40, 20, 30, 10, 30};
        int n = dims.length - 1;
        long[][] dp = new long[n][n];
        for (long[] row : dp) java.util.Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) dp[i][i] = 0;
        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j],
                        dp[i][k] + dp[k + 1][j] +
                        (long) dims[i] * dims[k + 1] * dims[j + 1]);
            }
        System.out.println("minimum multiplications: " + dp[0][n - 1]);   // 26000
    }
}
