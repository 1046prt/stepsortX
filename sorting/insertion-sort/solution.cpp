// sortsort · Insertion Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/insertion-sort

#include <bits/stdc++.h>
using namespace std;

// Insert each element into its place among the already-sorted prefix.
void insertionSort(vector<int>& arr) {
    int n = (int)arr.size();
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

int main() {
    vector<int> data = {12, 31, 25, 8, 32, 17};
    insertionSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
