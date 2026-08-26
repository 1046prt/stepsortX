// sortsort · Longest Repeated Substring
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lrs

#include <bits/stdc++.h>
using namespace std;

// Longest repeated substring via a DP table of common suffix lengths.
string longestRepeatedSubstring(const string& s) {
    int n = s.size();
    vector<vector<int>> dp(n + 1, vector<int>(n + 1, 0));
    int bestLen = 0, bestEnd = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
            if (s[i - 1] == s[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                if (dp[i][j] > bestLen) {
                    bestLen = dp[i][j];
                    bestEnd = i;
                }
            }
        }
    }
    return s.substr(bestEnd - bestLen, bestLen);
}

int main() {
    vector<string> texts = {"banana", "geeksforgeeks", "abcd"};
    for (const string& text : texts) {
        cout << text << " -> " << longestRepeatedSubstring(text) << endl;
    }
    return 0;
}
