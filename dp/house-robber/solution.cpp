// sortsort · House Robber
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/house-robber

#include <bits/stdc++.h>
using namespace std;

// Max loot when adjacent houses cannot both be robbed
int rob(const vector<int>& houses) {
    int prev2 = 0, prev1 = 0;  // best up to house i-2 and i-1
    for (int money : houses) {
        int best = max(prev1, prev2 + money);
        prev2 = prev1;
        prev1 = best;
    }
    return prev1;
}

int main() {
    vector<vector<int>> streets = {{2, 7, 9, 3, 1}, {1, 2, 3, 1}};
    for (const auto& street : streets) {
        cout << "Houses:";
        for (int h : street) cout << " " << h;
        cout << " -> max loot: " << rob(street) << endl;
    }
    return 0;
}
