// sortsort · Edit Distance
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/edit-distance

public class Main {
    // Minimum insert/delete/replace operations to transform a into b
    static int minDistance(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]), // delete, insert
                            dp[i - 1][j - 1]);                    // replace
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("horse -> ros: " + minDistance("horse", "ros"));
        System.out.println("intention -> execution: " + minDistance("intention", "execution"));
    }
}
