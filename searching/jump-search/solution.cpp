// sortsort · Jump Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/jump-search

#include <bits/stdc++.h>
using namespace std;

// Jump ahead in blocks of size sqrt(n), then scan the block.
int jumpSearch(const vector<int>& arr, int target) {
    int n = (int)arr.size();
    if (n == 0) return -1;
    int step = max(1, (int)sqrt((double)n));
    int prev = 0;
    int curr = min(step, n);
    while (arr[curr - 1] < target) {
        prev = curr;
        if (curr == n) return -1;
        curr = min(curr + step, n);
    }
    for (int i = prev; i < curr; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}

int main() {
    vector<int> data = {1, 3, 5, 7, 9, 12, 15, 18, 21};
    cout << "index of 12: " << jumpSearch(data, 12) << endl;
    cout << "index of 10: " << jumpSearch(data, 10) << endl;
    return 0;
}
