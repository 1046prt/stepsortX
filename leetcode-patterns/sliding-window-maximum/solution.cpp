// sortsort · Sliding Window Maximum
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-maximum

#include <bits/stdc++.h>
using namespace std;

vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    deque<int> dq;  // indices with strictly decreasing values
    vector<int> result;
    for (int i = 0; i < (int)nums.size(); i++) {
        while (!dq.empty() && nums[dq.back()] <= nums[i]) dq.pop_back();
        dq.push_back(i);
        if (dq.front() == i - k) dq.pop_front();
        if (i >= k - 1) result.push_back(nums[dq.front()]);
    }
    return result;
}

int main() {
    vector<int> nums = {1, 3, -1, -3, 5, 3, 6, 7};
    vector<int> res = maxSlidingWindow(nums, 3);
    for (size_t i = 0; i < res.size(); i++) {
        if (i > 0) cout << " ";
        cout << res[i];
    }
    cout << endl;
    return 0;
}
