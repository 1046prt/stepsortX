// sortsort · SOS DP (Sum Over Subsets)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sos-dp

static int[] sosDP(int[] arr) {
    int n = arr.length;
    int[] dp = arr.clone();
    for (int i = 0; i < n; i++)
        for (int mask = 0; mask < (1 << n); mask++)
            if ((mask & (1 << i)) != 0)
                dp[mask] += dp[mask ^ (1 << i)];
    return dp;
}
