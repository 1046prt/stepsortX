// sortsort · Knuth Optimization (Optimal BST)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knuth-optimization

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> freq = {4, 2, 6, 3};
    int n = freq.size();
    vector<int> pre(n + 1, 0);
    for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + freq[i];
    auto rng = [&](int i, int j) { return pre[j + 1] - pre[i]; };

    vector<vector<long long>> dp(n + 2, vector<long long>(n + 2, 0));
    vector<vector<int>> root(n + 2, vector<int>(n + 2, 0));
    for (int i = 1; i <= n; i++) { dp[i][i] = freq[i - 1]; root[i][i] = i - 1; }

    for (int len = 2; len <= n; len++)
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            int lo = root[i][j - 1], hi = root[i + 1][j];
            for (int k = lo; k <= hi; k++) {
                long long left = k > i ? dp[i][k - 1] : 0;
                long long right = k < j ? dp[k + 1][j] : 0;
                long long cost = left + right + rng(i - 1, j);
                if (cost < dp[i][j]) { dp[i][j] = cost; root[i][j] = k - 1; }
            }
        }
    cout << "optimal BST cost: " << dp[1][n] << endl;   // 26
}
