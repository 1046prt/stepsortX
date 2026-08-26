// sortsort · SOS DP (Sum Over Subsets)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sos-dp

vector<int> sosDP(vector<int>& arr) {
    int n = arr.size();
    vector<int> dp = arr;
    for (int i = 0; i < n; i++)
        for (int mask = 0; mask < (1 << n); mask++)
            if (mask & (1 << i))
                dp[mask] += dp[mask ^ (1 << i)];
    return dp;
}
