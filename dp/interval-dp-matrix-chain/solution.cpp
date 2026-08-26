// sortsort · Interval DP (Matrix Chain)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-dp-matrix-chain

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> dims = {40, 20, 30, 10, 30};
    int n = dims.size() - 1;
    vector<vector<long long>> dp(n, vector<long long>(n, LLONG_MAX));
    for (int i = 0; i < n; i++) dp[i][i] = 0;
    for (int len = 2; len <= n; len++)
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            for (int k = i; k < j; k++)
                dp[i][j] = min(dp[i][j],
                    dp[i][k] + dp[k + 1][j] +
                    (long long)dims[i] * dims[k + 1] * dims[j + 1]);
        }
    cout << "minimum multiplications: " << dp[0][n - 1] << endl;   // 26000
}
