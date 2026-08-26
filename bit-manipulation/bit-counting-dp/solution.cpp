// Stepsort · Counting Bits (DP)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-counting-dp

#include <bits/stdc++.h>
using namespace std;

vector<int> countingBits(int n) {
    vector<int> dp(n + 1, 0);
    for (int i = 1; i <= n; i++) {
        dp[i] = dp[i >> 1] + (i & 1);
    }
    return dp;
}

int main() {
    vector<int> dp = countingBits(16);
    for (int i = 0; i < (int)dp.size(); i++) {
        cout << i << " -> " << dp[i] << endl;
    }
    return 0;
}
