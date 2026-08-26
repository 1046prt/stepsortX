// Stepsort · Partition Equal Subset Sum
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/partition-equal-subset

#include <bits/stdc++.h>
using namespace std;

// True iff nums splits into two subsets with equal sums
bool canPartition(const vector<int>& nums) {
    int total = accumulate(nums.begin(), nums.end(), 0);
    if (total % 2 != 0) return false;
    int target = total / 2;
    vector<char> reachable(target + 1, 0);  // char avoids vector<bool> quirks
    reachable[0] = 1;                       // empty subset reaches sum 0
    for (int num : nums) {
        // iterate sums downward so each num is used at most once
        for (int s = target; s >= num; --s) {
            if (reachable[s - num]) reachable[s] = 1;
        }
    }
    return reachable[target] != 0;
}

int main() {
    cout << boolalpha;
    cout << "[1, 5, 11, 5] partitionable: " << canPartition({1, 5, 11, 5}) << endl;
    cout << "[1, 2, 3, 5] partitionable: " << canPartition({1, 2, 3, 5}) << endl;
    return 0;
}
