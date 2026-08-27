// Stepsort · Sliding Window Minimum
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-minimum

#include <bits/stdc++.h>
using namespace std;

vector<int> slidingWindowMinimum(vector<int>& nums, int k) {
    deque<int> dq;
    vector<int> result;
    for (int i = 0; i < (int)nums.size(); i++) {
        while (!dq.empty() && dq.front() < i - k + 1) dq.pop_front();
        while (!dq.empty() && nums[dq.back()] >= nums[i]) dq.pop_back();
        dq.push_back(i);
        if (i >= k - 1) result.push_back(nums[dq.front()]);
    }
    return result;
}

int main() {
    vector<int> nums = {1,3,-1,-3,5,3,6,7};
    auto res = slidingWindowMinimum(nums, 3);
    for (int x : res) cout << x << " ";
    cout << endl;
    return 0;
}
