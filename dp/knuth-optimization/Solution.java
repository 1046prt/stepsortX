// sortsort · Knuth Optimization (Optimal BST)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knuth-optimization

public class Main {
    public static void main(String[] args) {
        int[] freq = {4, 2, 6, 3};
        int n = freq.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + freq[i];
        java.util.function.BiFunction<Integer, Integer, Integer> rng =
            (i, j) -> pre[j + 1] - pre[i];

        long[][] dp = new long[n + 2][n + 2];
        int[][] root = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) { dp[i][i] = freq[i - 1]; root[i][i] = i - 1; }

        for (int len = 2; len <= n; len++)
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                int lo = root[i][j - 1], hi = root[i + 1][j];
                for (int k = lo; k <= hi; k++) {
                    long left = k > i ? dp[i][k - 1] : 0;
                    long right = k < j ? dp[k + 1][j] : 0;
                    long cost = left + right + rng.apply(i - 1, j);
                    if (cost < dp[i][j]) { dp[i][j] = cost; root[i][j] = k - 1; }
                }
            }
        System.out.println("optimal BST cost: " + dp[1][n]);   // 26
    }
}
