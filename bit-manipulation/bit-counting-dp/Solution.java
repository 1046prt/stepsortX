// sortsort · Counting Bits (DP)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-counting-dp

public class Main {
    static int[] countingBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] dp = countingBits(16);
        for (int i = 0; i < dp.length; i++) {
            System.out.println(i + " -> " + dp[i]);
        }
    }
}
