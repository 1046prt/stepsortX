// Stepsort · Subsets (Power Set)
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subsets

#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<int>& nums, int start, vector<int>& current,
               vector<vector<int>>& result) {
    result.push_back(current);  // every prefix is a valid subset
    for (int i = start; i < (int)nums.size(); i++) {
        current.push_back(nums[i]);
        backtrack(nums, i + 1, current, result);
        current.pop_back();
    }
}

vector<vector<int>> subsets(vector<int>& nums) {
    vector<vector<int>> result;
    vector<int> current;
    backtrack(nums, 0, current, result);
    return result;
}

int main() {
    vector<int> nums = {1, 2, 3};
    for (const vector<int>& subset : subsets(nums)) {
        cout << "[";
        for (size_t i = 0; i < subset.size(); i++) {
            if (i > 0) cout << ", ";
            cout << subset[i];
        }
        cout << "]" << endl;
    }
}
