// Stepsort · Edit Distance
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/edit-distance

#include <bits/stdc++.h>
using namespace std;

// Minimum insert/delete/replace operations to transform a into b
int minDistance(const string& a, const string& b) {
    int m = a.size(), n = b.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + min({dp[i - 1][j],      // delete
                                    dp[i][j - 1],      // insert
                                    dp[i - 1][j - 1]}); // replace
            }
        }
    }
    return dp[m][n];
}

int main() {
    cout << "horse -> ros: " << minDistance("horse", "ros") << endl;
    cout << "intention -> execution: " << minDistance("intention", "execution") << endl;
    return 0;
}
