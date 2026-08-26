// Stepsort · Quick Select
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/quick-select

#include <bits/stdc++.h>
using namespace std;

// deterministic pivot: value-sorted middle among first, middle, last
int medianOfThreeIndex(const vector<int>& arr, int lo, int hi) {
    int mid = (lo + hi) / 2;
    pair<int, int> trio[3] = {{arr[lo], lo}, {arr[mid], mid}, {arr[hi], hi}};
    sort(trio, trio + 3);
    return trio[1].second;
}

int partition(vector<int>& arr, int lo, int hi) {
    swap(arr[medianOfThreeIndex(arr, lo, hi)], arr[hi]);
    int pivot = arr[hi], store = lo;
    for (int i = lo; i < hi; i++)
        if (arr[i] < pivot) swap(arr[i], arr[store++]);
    swap(arr[store], arr[hi]);
    return store;
}

// k-th smallest (zero-indexed); takes a copy so the caller keeps order
int quickSelect(vector<int> arr, int k) {
    int lo = 0, hi = (int)arr.size() - 1;
    while (true) {
        if (lo == hi) return arr[lo];
        int p = partition(arr, lo, hi);
        if (k == p) return arr[p];
        if (k < p) hi = p - 1;
        else lo = p + 1;
    }
}

int main() {
    vector<int> data = {7, 2, 9, 4, 1, 8, 6, 3, 5};
    cout << "data:";
    for (int v : data) cout << " " << v;
    cout << endl;
    for (int k : {0, 3, 8})
        cout << "rank " << k + 1 << " smallest: " << quickSelect(data, k) << endl;
    return 0;
}
