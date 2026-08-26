// sortsort · Minimum Path Sum
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-path-sum

#include <bits/stdc++.h>
using namespace std;

// dp[j] = min path sum to reach cell in current row, column j
int minPathSum(vector<vector<int>>& grid) {
    int rows = grid.size(), cols = grid[0].size();
    vector<int> dp(cols);
    dp[0] = grid[0][0];
    for (int j = 1; j < cols; j++) dp[j] = dp[j - 1] + grid[0][j];
    for (int i = 1; i < rows; i++) {
        dp[0] += grid[i][0];
        for (int j = 1; j < cols; j++) {
            dp[j] = min(dp[j], dp[j - 1]) + grid[i][j];
        }
    }
    return dp[cols - 1];
}

int main() {
    vector<vector<int>> grid = {
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1},
    };
    cout << "Minimum path sum: " << minPathSum(grid) << endl;
    return 0;
}
