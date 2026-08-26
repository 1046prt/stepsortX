// sortsort · Heap Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-sort

#include <bits/stdc++.h>
using namespace std;

// Push arr[root] down until the subtree rooted there is a max-heap.
void siftDown(vector<int>& arr, int root, int size) {
    while (true) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        if (left < size && arr[left] > arr[largest]) largest = left;
        if (right < size && arr[right] > arr[largest]) largest = right;
        if (largest == root) return;
        swap(arr[root], arr[largest]);
        root = largest;
    }
}

void heapSort(vector<int>& arr) {
    int n = (int)arr.size();
    // Build a max-heap, then repeatedly move the max to the end.
    for (int i = n / 2 - 1; i >= 0; i--) siftDown(arr, i, n);
    for (int end = n - 1; end > 0; end--) {
        swap(arr[0], arr[end]);
        siftDown(arr, 0, end);
    }
}

int main() {
    vector<int> data = {4, 10, 3, 5, 1};
    heapSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
