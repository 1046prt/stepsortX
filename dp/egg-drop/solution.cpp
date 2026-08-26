// sortsort · Egg Drop Problem
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/egg-drop

#include <bits/stdc++.h>
using namespace std;

// dp[e][f] = minimum trials needed with e eggs and f floors
int eggDrop(int eggs, int floors) {
    vector<vector<long long>> dp(eggs + 1, vector<long long>(floors + 1, 0));
    for (int f = 1; f <= floors; f++) dp[1][f] = f;
    for (int e = 2; e <= eggs; e++) {
        for (int f = 1; f <= floors; f++) {
            long long best = LLONG_MAX;
            for (int x = 1; x <= f; x++) {  // drop from floor x
                long long worst = max(dp[e - 1][x - 1], dp[e][f - x]);
                best = min(best, 1 + worst);
            }
            dp[e][f] = best;
        }
    }
    return (int)dp[eggs][floors];
}

int main() {
    cout << "Egg drop with 2 eggs, 10 floors: " << eggDrop(2, 10) << endl;
    return 0;
}
