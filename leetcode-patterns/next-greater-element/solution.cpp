// Stepsort · Next Greater Element
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/next-greater-element

#include <bits/stdc++.h>
using namespace std;

vector<int> nextGreaterElement(vector<int>& nums) {
    int n = nums.size();
    vector<int> result(n, -1);
    stack<int> stk;
    for (int i = n - 1; i >= 0; i--) {
        while (!stk.empty() && stk.top() <= nums[i]) stk.pop();
        if (!stk.empty()) result[i] = stk.top();
        stk.push(nums[i]);
    }
    return result;
}

int main() {
    vector<int> nums = {4, 5, 2, 25};
    auto res = nextGreaterElement(nums);
    for (int x : res) cout << x << " ";
    cout << endl;
    return 0;
}
