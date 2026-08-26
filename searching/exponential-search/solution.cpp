// sortsort · Exponential Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/exponential-search

#include <bits/stdc++.h>
using namespace std;

int binarySearchRange(const vector<int>& arr, int target, int lo, int hi) {
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}

// Grow the bound exponentially, then binary search the last block.
int exponentialSearch(const vector<int>& arr, int target) {
    int n = (int)arr.size();
    if (n == 0) return -1;
    if (arr[0] == target) return 0;
    int bound = 1;
    while (bound < n && arr[bound] <= target) {
        bound *= 2;
    }
    return binarySearchRange(arr, target, bound / 2, min(bound, n - 1));
}

int main() {
    vector<int> data = {2, 4, 8, 16, 32, 64, 128, 256};
    cout << "index of 64: " << exponentialSearch(data, 64) << endl;
    cout << "index of 100: " << exponentialSearch(data, 100) << endl;
    return 0;
}
