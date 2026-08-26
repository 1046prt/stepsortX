// sortsort · Two Pointer Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-pointer-search

#include <bits/stdc++.h>
using namespace std;

// Walk both ends of a sorted array inward based on the current sum.
pair<int, int> twoPointerPairSum(const vector<int>& arr, int target) {
    int lo = 0, hi = (int)arr.size() - 1;
    while (lo < hi) {
        int total = arr[lo] + arr[hi];
        if (total == target) return make_pair(lo, hi);
        else if (total < target) lo++;
        else hi--;
    }
    return make_pair(-1, -1);
}

int main() {
    vector<int> data = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
    pair<int, int> hit = twoPointerPairSum(data, 24);
    pair<int, int> miss = twoPointerPairSum(data, 200);
    cout << "pair for 24: (" << hit.first << ", " << hit.second << ")" << endl;
    cout << "pair for 200: (" << miss.first << ", " << miss.second << ")" << endl;
    return 0;
}
