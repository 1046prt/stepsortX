// sortsort · Bitmask DP (TSP)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitmask-dp-tsp

#include <bits/stdc++.h>
using namespace std;

int main() {
    int dist[4][4] = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
    int n = 4, FULL = (1 << n) - 1;
    vector<vector<int>> dp(1 << n, vector<int>(n, INT_MAX));
    dp[1][0] = 0;
    for (int mask = 1; mask <= FULL; mask += 2)
        for (int last = 0; last < n; last++) {
            if (!(mask & (1 << last)) || dp[mask][last] == INT_MAX) continue;
            for (int nxt = 0; nxt < n; nxt++) {
                if (mask & (1 << nxt)) continue;
                int nm = mask | (1 << nxt);
                dp[nm][nxt] = min(dp[nm][nxt], dp[mask][last] + dist[last][nxt]);
            }
        }
    int best = INT_MAX;
    for (int last = 1; last < n; last++)
        best = min(best, dp[FULL][last] + dist[last][0]);
    cout << "optimal tour cost: " << best << endl;   // 80
}
