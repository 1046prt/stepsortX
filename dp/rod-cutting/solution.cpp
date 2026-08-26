// sortsort · Rod Cutting
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rod-cutting

#include <bits/stdc++.h>
using namespace std;

// prices[k] is the price of a piece of length k+1.
pair<int, vector<int>> rodCutting(const vector<int>& prices, int n) {
    vector<int> dp(n + 1, 0);           // dp[L]: best revenue for length L
    vector<vector<int>> cuts(n + 1);    // piece sizes achieving dp[L]
    for (int length = 1; length <= n; ++length) {
        for (int first = 1; first <= length; ++first) {
            int candidate = prices[first - 1] + dp[length - first];
            if (candidate > dp[length]) {
                dp[length] = candidate;
                cuts[length] = cuts[length - first];
                cuts[length].push_back(first);
            }
        }
    }
    return {dp[n], cuts[n]};
}

int main() {
    vector<int> prices = {1, 5, 8, 9, 10, 17, 17, 20};
    auto [revenue, pieces] = rodCutting(prices, 8);
    cout << "Best revenue: " << revenue << endl;
    cout << "Piece lengths:";
    for (int p : pieces) cout << " " << p;
    cout << endl;
    return 0;
}
