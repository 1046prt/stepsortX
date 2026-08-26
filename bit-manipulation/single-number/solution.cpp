// sortsort · Single Number (XOR)
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/single-number

#include <bits/stdc++.h>
using namespace std;

// XOR of pairs cancels out, leaving the unique element
int singleNumber(vector<int>& nums) {
    int result = 0;
    for (int num : nums) result ^= num;
    return result;
}

int main() {
    vector<int> a = {4, 1, 2, 1, 2};
    vector<int> b = {2, 2, 1};
    vector<int> c = {7};
    cout << singleNumber(a) << endl;
    cout << singleNumber(b) << endl;
    cout << singleNumber(c) << endl;
    return 0;
}
