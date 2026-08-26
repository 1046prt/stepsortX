// Stepsort · Knapsack Optimizations
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack-optimizations

int knapsackBitset(vector<int>& wt, vector<int>& val, int W) {
    vector<int> dp(W+1, 0);
    for (int i = 0; i < (int)wt.size(); i++)
        for (int w = W; w >= wt[i]; w--)
            dp[w] = max(dp[w], dp[w-wt[i]] + val[i]);
    return dp[W];
}

int knapsackMITM(vector<pair<int,int>>& items, int W) {
    int n = items.size(), mid = n/2;
    auto gen = [&](int s, int e) {
        vector<pair<int,int>> res;
        for (int mask = 0; mask < (1<<(e-s)); mask++) {
            int w = 0, v = 0;
            for (int i = 0; i < e-s; i++)
                if (mask & (1<<i)) w += items[s+i].first, v += items[s+i].second;
            if (w <= W) res.push_back({w, v});
        }
        return res;
    };
    auto left = gen(0, mid), right = gen(mid, n);
    sort(right.begin(), right.end());
    int best = 0, j = right.size()-1;
    for (auto [w, v] : left) {
        while (j >= 0 && right[j].first + w > W) j--;
        if (j >= 0) best = max(best, v + right[j].second);
    }
    return best;
}
