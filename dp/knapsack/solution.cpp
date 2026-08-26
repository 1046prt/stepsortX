// Stepsort · 0/1 Knapsack
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack

#include <bits/stdc++.h>
using namespace std;

pair<int, vector<int>> knapsack(const vector<int>& weights, const vector<int>& values, int capacity) {
    int n = weights.size();
    // dp[i][w] = best value using the first i items with capacity w
    vector<vector<int>> dp(n + 1, vector<int>(capacity + 1, 0));
    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= capacity; w++) {
            dp[i][w] = dp[i - 1][w];  // skip item i-1
            if (weights[i - 1] <= w) {
                int take = dp[i - 1][w - weights[i - 1]] + values[i - 1];
                dp[i][w] = max(dp[i][w], take);
            }
        }
    }
    // Walk back through the table to recover the chosen items
    vector<int> chosen;
    int w = capacity;
    for (int i = n; i >= 1; i--) {
        if (dp[i][w] != dp[i - 1][w]) {
            chosen.push_back(i - 1);
            w -= weights[i - 1];
        }
    }
    reverse(chosen.begin(), chosen.end());
    return {dp[n][capacity], chosen};
}

int main() {
    vector<int> weights = {1, 3, 4, 5};
    vector<int> values = {1, 4, 5, 7};
    auto result = knapsack(weights, values, 7);
    cout << "Best achievable value: " << result.first << endl;
    for (int i : result.second) {
        cout << "  item " << i << ": weight=" << weights[i] << ", value=" << values[i] << endl;
    }
    return 0;
}
