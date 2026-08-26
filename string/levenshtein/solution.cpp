// sortsort · Levenshtein Distance
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/levenshtein

#include <bits/stdc++.h>
using namespace std;

// Full DP table: dp[i][j] = edits to turn a[:i] into b[:j].
int levenshteinDistance(const string& a, const string& b) {
    int rows = a.size() + 1, cols = b.size() + 1;
    vector<vector<int>> dp(rows, vector<int>(cols, 0));
    for (int i = 0; i < rows; i++) dp[i][0] = i;
    for (int j = 0; j < cols; j++) dp[0][j] = j;

    for (int i = 1; i < rows; i++) {
        for (int j = 1; j < cols; j++) {
            int cost = (a[i - 1] == b[j - 1]) ? 0 : 1;
            dp[i][j] = min({dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + cost});
        }
    }
    return dp[rows - 1][cols - 1];
}

int main() {
    vector<pair<string, string>> tests = {
        {"kitten", "sitting"}, {"flaw", "lawn"}, {"", "abc"}};
    for (auto& [x, y] : tests) {
        cout << x << " vs " << y << " -> " << levenshteinDistance(x, y) << endl;
    }
    return 0;
}
