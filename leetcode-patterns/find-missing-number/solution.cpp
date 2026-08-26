// sortsort · Find Missing Number
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-missing-number

#include <bits/stdc++.h>
using namespace std;

int missingNumber(vector<int>& nums) {
    long long n = nums.size();
    long long expected = n * (n + 1) / 2;
    long long actual = accumulate(nums.begin(), nums.end(), 0LL);
    return (int)(expected - actual);
}

int main() {
    vector<int> nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
    cout << missingNumber(nums) << endl;
    return 0;
}
