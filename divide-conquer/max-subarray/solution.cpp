// Stepsort · Maximum Subarray
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-subarray

#include <bits/stdc++.h>
using namespace std;

// Kadane O(n) scan while tracking the best window for reconstruction
void maxSubarray(const vector<int>& nums, int& bestSum, int& start, int& end) {
    bestSum = nums[0];
    int current = nums[0];
    int tempStart = 0;
    start = end = 0;
    for (int i = 1; i < (int)nums.size(); i++) {
        if (nums[i] > current + nums[i]) {
            current = nums[i];
            tempStart = i;
        } else {
            current += nums[i];
        }
        if (current > bestSum) {
            bestSum = current;
            start = tempStart;
            end = i;
        }
    }
}

int main() {
    vector<int> nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int sum, start, end;
    maxSubarray(nums, sum, start, end);
    cout << "array:";
    for (int v : nums) cout << " " << v;
    cout << endl;
    cout << "max sum: " << sum << endl;
    cout << "subarray:";
    for (int i = start; i <= end; i++) cout << " " << nums[i];
    cout << endl;
    cout << "range: indices " << start << " to " << end << endl;
    return 0;
}
