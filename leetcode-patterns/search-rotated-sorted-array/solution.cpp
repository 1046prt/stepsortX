// sortsort · Jump Game
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/search-rotated-sorted-array

#include <bits/stdc++.h>
using namespace std;

int search(const vector<int>& nums, int target) {
    // Modified binary search: one half is always sorted.
    int left = 0, right = (int)nums.size() - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        if (nums[left] <= nums[mid]) {  // left half sorted
            if (target >= nums[left] && target < nums[mid]) right = mid - 1;
            else left = mid + 1;
        } else {  // right half sorted
            if (target > nums[mid] && target <= nums[right]) left = mid + 1;
            else right = mid - 1;
        }
    }
    return -1;
}

int main() {
    vector<int> nums = {4, 5, 6, 7, 0, 1, 2};
    cout << search(nums, 0) << endl;
    cout << search(nums, 4) << endl;
    cout << search(nums, 3) << endl;
}
