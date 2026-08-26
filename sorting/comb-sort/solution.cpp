// sortsort · Comb Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/comb-sort

#include <bits/stdc++.h>
using namespace std;

// Compare items gap apart, shrinking the gap by a factor of 1.3.
void combSort(vector<int>& arr) {
    int n = (int)arr.size();
    int gap = n;
    bool swapped = true;
    while (gap > 1 || swapped) {
        gap = max(1, (int)(gap / 1.3));
        swapped = false;
        for (int i = 0; i + gap < n; i++) {
            if (arr[i] > arr[i + gap]) {
                swap(arr[i], arr[i + gap]);
                swapped = true;
            }
        }
    }
}

int main() {
    vector<int> data = {8, 4, 1, 56, 3, 44, 20};
    combSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
