// Stepsort · IntroSort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/intro-sort

#include <bits/stdc++.h>
using namespace std;

void insertionSort(vector<int>& arr, int lo, int hi) {
    for (int i = lo + 1; i < hi; i++) {
        int key = arr[i], j = i - 1;
        while (j >= lo && arr[j] > key) { arr[j+1] = arr[j]; j--; }
        arr[j+1] = key;
    }
}

void heapSort(vector<int>& arr, int lo, int hi) {
    priority_queue<int, vector<int>, greater<int>> pq(arr.begin()+lo, arr.begin()+hi);
    for (int i = lo; i < hi; i++) { arr[i] = pq.top(); pq.pop(); }
}

int medianOfThree(vector<int>& arr, int lo, int hi) {
    int mid = lo + (hi - lo) / 2;
    if (arr[lo] > arr[mid]) swap(arr[lo], arr[mid]);
    if (arr[lo] > arr[hi-1]) swap(arr[lo], arr[hi-1]);
    if (arr[mid] > arr[hi-1]) swap(arr[mid], arr[hi-1]);
    swap(arr[mid], arr[hi-2]);
    return arr[hi-2];
}

void introSortUtil(vector<int>& arr, int lo, int hi, int depthLimit) {
    while (hi - lo > 16) {
        if (depthLimit == 0) { heapSort(arr, lo, hi); return; }
        depthLimit--;
        int pivot = medianOfThree(arr, lo, hi);
        int i = lo, j = hi - 2;
        while (true) {
            while (arr[++i] < pivot);
            while (arr[--j] > pivot);
            if (i >= j) break;
            swap(arr[i], arr[j]);
        }
        swap(arr[i], arr[hi-2]);
        introSortUtil(arr, i+1, hi, depthLimit);
        hi = i;
    }
    insertionSort(arr, lo, hi);
}

void introSort(vector<int>& arr) {
    int n = arr.size();
    if (n <= 1) return;
    introSortUtil(arr, 0, n, 2 * (int)log2(n));
}

int main() {
    vector<int> data = {5, 1, 4, 2, 8};
    introSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
