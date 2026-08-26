// sortsort · Bitonic Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitonic-sort

#include <bits/stdc++.h>
using namespace std;

void compareAndSwap(vector<int>& arr, int i, int j, bool dir) {
    if ((dir && arr[i] > arr[j]) || (!dir && arr[i] < arr[j]))
        swap(arr[i], arr[j]);
}

void bitonicMerge(vector<int>& arr, int low, int count, bool dir) {
    if (count <= 1) return;
    int mid = count / 2;
    for (int i = low; i < low + mid; i++)
        compareAndSwap(arr, i, i + mid, dir);
    bitonicMerge(arr, low, mid, dir);
    bitonicMerge(arr, low + mid, mid, dir);
}

// Sorting network for power-of-two lengths.
void bitonicSort(vector<int>& arr, int low, int count, bool dir) {
    if (count <= 1) return;
    int mid = count / 2;
    bitonicSort(arr, low, mid, true);
    bitonicSort(arr, low + mid, mid, false);
    bitonicMerge(arr, low, count, dir);
}

int main() {
    vector<int> data = {3, 7, 4, 8, 6, 2, 1, 5};
    bitonicSort(data, 0, (int)data.size(), true);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
