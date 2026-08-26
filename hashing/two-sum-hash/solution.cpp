// Stepsort · Two Sum (Hash Map)
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-sum-hash

#include <bits/stdc++.h>
using namespace std;

vector<int> two_sum(const vector<int>& nums, int target) {
    unordered_map<int, int> seen;  // value -> index
    for (int i = 0; i < (int)nums.size(); i++) {
        int need = target - nums[i];
        auto it = seen.find(need);
        if (it != seen.end()) return {it->second, i};
        seen[nums[i]] = i;
    }
    return {};
}

int main() {
    vector<int> nums = {2, 7, 11, 15, 3, 6};
    int targets[] = {9, 18, 30};
    for (int target : targets) {
        vector<int> pair = two_sum(nums, target);
        if (pair.empty()) {
            cout << "target " << target << " -> no pair found" << endl;
        } else {
            cout << "target " << target << " -> indices "
                 << pair[0] << "," << pair[1]
                 << " values " << nums[pair[0]] << "," << nums[pair[1]]
                 << endl;
        }
    }
    return 0;
}
