// Stepsort · Pigeonhole Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pigeonhole-sort

#include <bits/stdc++.h>
using namespace std;

void pigeonholeSort(vector<int>& arr) {
    if (arr.empty()) return;
    int lo = *min_element(arr.begin(), arr.end());
    int hi = *max_element(arr.begin(), arr.end());
    vector<int> holes(hi - lo + 1, 0);
    for (int value : arr) holes[value - lo]++;
    int i = 0;
    for (int offset = 0; offset < (int)holes.size(); offset++) {
        while (holes[offset] > 0) {
            arr[i++] = offset + lo;
            holes[offset]--;
        }
    }
}

int main() {
    vector<int> data = {9, 3, 7, 1, 8, 3, 5};
    pigeonholeSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
