// Stepsort · Boyer-Moore Majority Vote
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore-majority-vote

#include <bits/stdc++.h>
using namespace std;

int majorityElement(vector<int>& nums) {
    int candidate = 0, count = 0;
    for (int num : nums) {
        if (count == 0) { candidate = num; count = 1; }
        else if (num == candidate) count++;
        else count--;
    }
    // Verify
    int occurrences = count_if(nums.begin(), nums.end(),
                               [&](int x){ return x == candidate; });
    return occurrences > (int)nums.size() / 2 ? candidate : -1;
}

int main() {
    vector<int> nums = {3, 3, 4, 2, 3, 3, 3};
    cout << "majority: " << majorityElement(nums) << endl;
    return 0;
}
