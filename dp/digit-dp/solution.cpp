// Stepsort · Digit DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/digit-dp

#include <bits/stdc++.h>
using namespace std;

string N;
long long memo[20][10][2];

long long dfs(int pos, int prevDigit, bool tight, bool started) {
    if (pos == (int)N.size()) return started ? 1 : 0;
    if (!tight && memo[pos][prevDigit][started] != -1)
        return memo[pos][prevDigit][started];
    int maxD = tight ? N[pos] - '0' : 9;
    long long total = 0;
    for (int d = 0; d <= maxD; d++) {
        if (started && d < prevDigit) continue;
        total += dfs(pos + 1, d, tight && d == maxD, started || d > 0);
    }
    if (!tight) memo[pos][prevDigit][started] = total;
    return total;
}

int main() {
    N = "356";
    memset(memo, -1, sizeof(memo));
    cout << dfs(0, 0, true, false) << endl;   // 84
}
