// Stepsort · Coin Change
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-change

#include <bits/stdc++.h>
using namespace std;

int coinChange(const vector<int>& coins, int amount) {
    // Bottom-up DP over amounts; sentinel amount+1 means unreachable.
    const int INF = amount + 1;
    vector<int> dp(amount + 1, INF);
    dp[0] = 0;
    for (int coin : coins) {
        for (int x = coin; x <= amount; x++) {
            dp[x] = min(dp[x], dp[x - coin] + 1);
        }
    }
    return dp[amount] >= INF ? -1 : dp[amount];
}

int main() {
    vector<int> coins = {1, 2, 5};
    cout << coinChange(coins, 11) << endl; // 3
    vector<int> onlyTwo = {2};
    cout << coinChange(onlyTwo, 3) << endl; // -1
}
