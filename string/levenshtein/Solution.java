// sortsort · Levenshtein Distance
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/levenshtein

public class Main {
    // Full DP table: dp[i][j] = edits to turn a[:i] into b[:j].
    static int levenshteinDistance(String a, String b) {
        int rows = a.length() + 1, cols = b.length() + 1;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) dp[i][0] = i;
        for (int j = 0; j < cols; j++) dp[0][j] = j;

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1,
                                     dp[i - 1][j - 1] + cost));
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        String[][] tests = {{"kitten", "sitting"}, {"flaw", "lawn"}, {"", "abc"}};
        for (String[] pair : tests) {
            System.out.println(pair[0] + " vs " + pair[1]
                    + " -> " + levenshteinDistance(pair[0], pair[1]));
        }
    }
}
