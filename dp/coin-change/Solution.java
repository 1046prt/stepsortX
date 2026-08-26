// Stepsort · Coin Change
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-change

import java.util.Arrays;

public class Main {
    public static int coinChange(int[] coins, int amount) {
        // Bottom-up DP over amounts; sentinel amount+1 means unreachable.
        int inf = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                if (dp[x - coin] + 1 < dp[x]) dp[x] = dp[x - coin] + 1;
            }
        }
        return dp[amount] >= inf ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(coinChange(new int[]{2}, 3));        // -1
    }
}
