// Stepsort · Longest Common Subsequence
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lcs

public class Main {
    static void lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int[][] parent = new int[m + 1][n + 1]; // 1=diag 2=up 3=left
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    parent[i][j] = 1;
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    parent[i][j] = 2;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    parent[i][j] = 3;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (parent[i][j] == 1) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (parent[i][j] == 2) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println("LCS of " + s1 + " and " + s2 + ": length=" + dp[m][n]);
        System.out.println("LCS sequence: " + sb.reverse().toString());
    }

    public static void main(String[] args) {
        lcs("AGGTAB", "GXTXAYB");
    }
}
