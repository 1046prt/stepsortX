// Stepsort · Ternary Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ternary-search

#include <bits/stdc++.h>
using namespace std;

// Split the sorted range into three parts using two midpoints.
int ternarySearch(const vector<int>& arr, int target) {
    int lo = 0, hi = (int)arr.size() - 1;
    while (lo <= hi) {
        int third = (hi - lo) / 3;
        int m1 = lo + third;
        int m2 = hi - third;
        if (arr[m1] == target) return m1;
        if (arr[m2] == target) return m2;
        if (target < arr[m1]) {
            hi = m1 - 1;
        } else if (target > arr[m2]) {
            lo = m2 + 1;
        } else {
            lo = m1 + 1;
            hi = m2 - 1;
        }
    }
    return -1;
}

int main() {
    vector<int> data = {1, 4, 7, 12, 15, 19, 24, 31, 40};
    cout << "index of 19: " << ternarySearch(data, 19) << endl;
    cout << "index of 20: " << ternarySearch(data, 20) << endl;
    return 0;
}
