// sortsort · Longest Repeated Substring
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lrs

public class Main {
    // Longest repeated substring via a DP table of common suffix lengths.
    static String longestRepeatedSubstring(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        int bestLen = 0, bestEnd = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > bestLen) {
                        bestLen = dp[i][j];
                        bestEnd = i;
                    }
                }
            }
        }
        return s.substring(bestEnd - bestLen, bestEnd);
    }

    public static void main(String[] args) {
        String[] texts = {"banana", "geeksforgeeks", "abcd"};
        for (String text : texts) {
            System.out.println(text + " -> " + longestRepeatedSubstring(text));
        }
    }
}
