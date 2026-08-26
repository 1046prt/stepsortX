// sortsort · Maximal Square
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/maximal-square

#include <bits/stdc++.h>
using namespace std;

// dp[i][j] = side of largest square with bottom-right corner at (i-1, j-1)
int maximalSquare(vector<vector<char>>& matrix) {
    int rows = matrix.size(), cols = matrix[0].size();
    vector<vector<int>> dp(rows + 1, vector<int>(cols + 1, 0));
    int side = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (matrix[i][j] == '1') {
                dp[i + 1][j + 1] =
                    min({dp[i][j], dp[i + 1][j], dp[i][j + 1]}) + 1;
                side = max(side, dp[i + 1][j + 1]);
            }
        }
    }
    return side * side;
}

int main() {
    vector<vector<char>> matrix = {
        {'1', '0', '1', '0', '0'},
        {'1', '0', '1', '1', '1'},
        {'1', '1', '1', '1', '1'},
        {'1', '0', '0', '1', '0'},
    };
    cout << "Largest square area: " << maximalSquare(matrix) << endl;
    return 0;
}
