// Stepsort · Bubble Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bubble-sort

#include <bits/stdc++.h>
using namespace std;

// Repeatedly swap adjacent out-of-order pairs; stop early if a pass is clean.
void bubbleSort(vector<int>& arr) {
    int n = (int)arr.size();
    for (int i = 0; i < n - 1; i++) {
        bool swapped = false;
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr[j], arr[j + 1]);
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}

int main() {
    vector<int> data = {5, 1, 4, 2, 8};
    bubbleSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
