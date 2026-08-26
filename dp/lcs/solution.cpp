// Stepsort · Longest Common Subsequence
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lcs

#include <bits/stdc++.h>
using namespace std;

pair<int, string> lcs(const string& s1, const string& s2) {
    int m = s1.size(), n = s2.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    vector<vector<int>> parent(m + 1, vector<int>(n + 1, 0)); // 1=diag 2=up 3=left
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                parent[i][j] = 1;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                dp[i][j] = dp[i - 1][j];
                parent[i][j] = 2;
            } else {
                dp[i][j] = dp[i][j - 1];
                parent[i][j] = 3;
            }
        }
    }
    // Follow parent pointers from (m, n) back to (0, 0)
    string chars;
    int i = m, j = n;
    while (i > 0 && j > 0) {
        if (parent[i][j] == 1) {
            chars += s1[i - 1];
            i--;
            j--;
        } else if (parent[i][j] == 2) {
            i--;
        } else {
            j--;
        }
    }
    reverse(chars.begin(), chars.end());
    return {dp[m][n], chars};
}

int main() {
    string s1 = "AGGTAB", s2 = "GXTXAYB";
    auto result = lcs(s1, s2);
    cout << "LCS of " << s1 << " and " << s2 << ": length=" << result.first;
    cout << ", sequence=" << result.second << endl;
    return 0;
}
