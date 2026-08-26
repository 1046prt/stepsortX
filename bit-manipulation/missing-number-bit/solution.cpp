// Stepsort · Missing Number (XOR)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/missing-number-bit

#include <bits/stdc++.h>
using namespace std;

int missingNumber(const vector<int>& nums) {
    int n = nums.size();
    int result = 0;
    for (int v = 0; v <= n; v++) {
        result ^= v;
    }
    for (int v : nums) {
        result ^= v;
    }
    return result;
}

int main() {
    vector<int> nums = {0, 1, 2, 4};
    cout << "missing: " << missingNumber(nums) << endl;
    return 0;
}
