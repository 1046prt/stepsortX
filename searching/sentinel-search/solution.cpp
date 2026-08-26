// Stepsort · Sentinel Linear Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sentinel-search

#include <bits/stdc++.h>
using namespace std;

// Park the target at the end so the scan needs no bounds check.
int sentinelSearch(vector<int> arr, int target) {
    int n = (int)arr.size();
    if (n == 0) return -1;
    int last = arr[n - 1];
    arr[n - 1] = target;
    int i = 0;
    while (arr[i] != target) i++;
    arr[n - 1] = last;
    if (i < n - 1 || arr[n - 1] == target) return i;
    return -1;
}

int main() {
    vector<int> data = {4, 2, 7, 1, 9, 5};
    cout << "index of 9: " << sentinelSearch(data, 9) << endl;
    cout << "index of 5: " << sentinelSearch(data, 5) << endl;
    cout << "index of 3: " << sentinelSearch(data, 3) << endl;
    return 0;
}
