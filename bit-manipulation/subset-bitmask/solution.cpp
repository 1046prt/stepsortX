// Stepsort · Subsets via Bitmask
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-bitmask

#include <bits/stdc++.h>
using namespace std;

void printSubsets(const vector<int>& arr) {
    int n = arr.size();
    cout << "subsets of size " << n << endl;
    for (int mask = 0; mask < (1 << n); mask++) {
        cout << mask << " -> {";
        bool first = true;
        for (int i = 0; i < n; i++) {
            if (mask & (1 << i)) {
                if (!first) cout << ", ";
                cout << arr[i];
                first = false;
            }
        }
        cout << "}" << endl;
    }
}

int main() {
    vector<int> arr = {1, 2, 3};
    printSubsets(arr);
    return 0;
}
