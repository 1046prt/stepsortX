// sortsort · Palindrome Partitioning
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindrome-partition

#include <bits/stdc++.h>
using namespace std;

// isPal[i][j] is true when s[i..j] is a palindrome
int minCut(const string& s) {
    int n = s.size();
    if (n <= 1) return 0;
    vector<vector<bool>> isPal(n, vector<bool>(n, false));
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            if (s[i] == s[j] && (j - i < 2 || isPal[i + 1][j - 1])) {
                isPal[i][j] = true;
            }
        }
    }
    // cut[j] = minimum cuts needed for prefix s[0..j]
    vector<int> cut(n);
    for (int j = 0; j < n; j++) {
        if (isPal[0][j]) {
            cut[j] = 0;
            continue;
        }
        int best = j;
        for (int i = 1; i <= j; i++) {
            if (isPal[i][j]) best = min(best, cut[i - 1] + 1);
        }
        cut[j] = best;
    }
    return cut[n - 1];
}

int main() {
    cout << "Min cuts for 'aab': " << minCut("aab") << endl;
    return 0;
}
