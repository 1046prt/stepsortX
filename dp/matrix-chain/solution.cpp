// sortsort · Matrix Chain Multiplication
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/matrix-chain

#include <bits/stdc++.h>
using namespace std;

const int INF = numeric_limits<int>::max() / 2;

// Matrix i has dimensions dims[i] x dims[i+1]
string buildParens(const vector<vector<int>>& split, int i, int j) {
    if (i == j) return string(1, 'A' + i);
    int k = split[i][j];
    return "(" + buildParens(split, i, k) + buildParens(split, k + 1, j) + ")";
}

void matrixChain(const vector<int>& dims) {
    int n = dims.size() - 1;
    vector<vector<int>> dp(n, vector<int>(n, 0));
    vector<vector<int>> split(n, vector<int>(n, 0));
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            dp[i][j] = INF;
            for (int k = i; k < j; k++) {
                int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                if (cost < dp[i][j]) {
                    dp[i][j] = cost;
                    split[i][j] = k;
                }
            }
        }
    }
    cout << "Minimum scalar multiplications: " << dp[0][n - 1] << endl;
    cout << "Optimal parenthesization: " << buildParens(split, 0, n - 1) << endl;
}

int main() {
    matrixChain({10, 30, 5, 60});
    return 0;
}
