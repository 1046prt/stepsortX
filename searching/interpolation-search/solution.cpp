// sortsort · Interpolation Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interpolation-search

#include <bits/stdc++.h>
using namespace std;

// Probe position estimated from value distribution (sorted data).
int interpolationSearch(const vector<int>& arr, int target) {
    int lo = 0, hi = (int)arr.size() - 1;
    while (lo <= hi && arr[lo] <= target && target <= arr[hi]) {
        if (arr[lo] == arr[hi]) {
            return arr[lo] == target ? lo : -1;
        }
        int pos = lo + (int)((long long)(target - arr[lo]) * (hi - lo)
                             / (arr[hi] - arr[lo]));
        if (arr[pos] == target) return pos;
        else if (arr[pos] < target) lo = pos + 1;
        else hi = pos - 1;
    }
    return -1;
}

int main() {
    vector<int> data = {10, 20, 30, 40, 50, 60, 70, 80};
    cout << "index of 50: " << interpolationSearch(data, 50) << endl;
    cout << "index of 45: " << interpolationSearch(data, 45) << endl;
    return 0;
}
