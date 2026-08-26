// Stepsort · 0/1 Knapsack
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack

public class Main {
    static void knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[i][w] = best value using the first i items with capacity w
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w]; // skip item i-1
                if (weights[i - 1] <= w) {
                    int take = dp[i - 1][w - weights[i - 1]] + values[i - 1];
                    if (take > dp[i][w]) dp[i][w] = take;
                }
            }
        }
        System.out.println("Best achievable value: " + dp[n][capacity]);
        // Walk back through the table to recover the chosen items
        int[] chosen = new int[n];
        int cnt = 0, w = capacity;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                chosen[cnt++] = i - 1;
                w -= weights[i - 1];
            }
        }
        for (int k = cnt - 1; k >= 0; k--) {
            int i = chosen[k];
            System.out.println("  item " + i + ": weight=" + weights[i] + ", value=" + values[i]);
        }
    }

    public static void main(String[] args) {
        knapsack(new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 7);
    }
}
