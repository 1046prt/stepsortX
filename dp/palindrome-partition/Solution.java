// Stepsort · Palindrome Partitioning
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindrome-partition

public class Main {
    // isPal[i][j] is true when s[i..j] is a palindrome
    static int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }
        // cut[j] = minimum cuts needed for prefix s[0..j]
        int[] cut = new int[n];
        for (int j = 0; j < n; j++) {
            if (isPal[0][j]) {
                cut[j] = 0;
                continue;
            }
            int best = j;
            for (int i = 1; i <= j; i++) {
                if (isPal[i][j]) best = Math.min(best, cut[i - 1] + 1);
            }
            cut[j] = best;
        }
        return cut[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Min cuts for 'aab': " + minCut("aab"));
    }
}
