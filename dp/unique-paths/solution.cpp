// sortsort · Unique Paths
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/unique-paths

#include <bits/stdc++.h>
using namespace std;

// dp[j] holds ways to reach column j while sweeping rows top to bottom
int uniquePaths(int m, int n) {
    vector<int> dp(n, 1);
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[j] += dp[j - 1];
        }
    }
    return dp[n - 1];
}

int main() {
    cout << "Unique paths in a 3 x 7 grid: " << uniquePaths(3, 7) << endl;
    return 0;
}
