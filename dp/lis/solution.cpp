// Stepsort · Longest Increasing Subsequence
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis

#include <bits/stdc++.h>
using namespace std;

pair<int, vector<int>> lis(const vector<int>& nums) {
    int n = nums.size();
    vector<int> tails, tailIdx;   // tails[k] = smallest tail of length k+1 subsequence
    vector<int> prev(n, -1);      // predecessor index for reconstruction
    for (int i = 0; i < n; i++) {
        int pos = lower_bound(tails.begin(), tails.end(), nums[i]) - tails.begin();
        if (pos == (int)tails.size()) {
            tails.push_back(nums[i]);
            tailIdx.push_back(i);
        } else {
            tails[pos] = nums[i];
            tailIdx[pos] = i;
        }
        prev[i] = pos > 0 ? tailIdx[pos - 1] : -1;
    }
    // Walk predecessors from the last tail to rebuild one LIS
    vector<int> seq;
    for (int k = tailIdx.empty() ? -1 : tailIdx.back(); k >= 0; k = prev[k]) {
        seq.push_back(nums[k]);
    }
    reverse(seq.begin(), seq.end());
    return {(int)tails.size(), seq};
}

int main() {
    vector<int> nums = {10, 9, 2, 5, 3, 7, 101, 18};
    auto result = lis(nums);
    cout << "LIS length: " << result.first << endl;
    cout << "One LIS:";
    for (int x : result.second) cout << " " << x;
    cout << endl;
    return 0;
}
