// sortsort · Binary Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search

#include <bits/stdc++.h>
using namespace std;

// Iterative search on a sorted array.
int binarySearch(const vector<int>& arr, int target) {
    int lo = 0, hi = (int)arr.size() - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}

int main() {
    vector<int> data = {1, 3, 5, 7, 9, 11, 13};
    cout << "index of 7: " << binarySearch(data, 7) << endl;
    cout << "index of 4: " << binarySearch(data, 4) << endl;
    return 0;
}
