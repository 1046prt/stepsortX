// sortsort · Linear Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linear-search

#include <bits/stdc++.h>
using namespace std;

// Scan every element from left to right.
int linearSearch(const vector<int>& arr, int target) {
    for (int i = 0; i < (int)arr.size(); i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}

int main() {
    vector<int> data = {4, 2, 7, 1, 9, 5};
    cout << "index of 7: " << linearSearch(data, 7) << endl;
    cout << "index of 3: " << linearSearch(data, 3) << endl;
    return 0;
}
