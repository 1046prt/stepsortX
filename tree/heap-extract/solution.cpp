// Stepsort · Heap Extract Max
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-extract

// Max-heap extract-max: swap root with last, pop, sift down
#include <bits/stdc++.h>
using namespace std;

// push a[i] down until it dominates both children
void siftDown(vector<int>& a, int i, int size) {
    while (true) {
        int largest = i;
        int left = 2 * i + 1, right = 2 * i + 2;
        if (left < size && a[left] > a[largest]) largest = left;
        if (right < size && a[right] > a[largest]) largest = right;
        if (largest == i) return;
        swap(a[i], a[largest]);
        i = largest;
    }
}

void buildHeap(vector<int>& a) {
    for (int i = static_cast<int>(a.size()) / 2 - 1; i >= 0; --i) {
        siftDown(a, i, static_cast<int>(a.size()));
    }
}

// swap root with last, shrink, then sift the new root down
int extractMax(vector<int>& a) {
    int top = a[0];
    a[0] = a.back();
    a.pop_back();
    if (!a.empty()) siftDown(a, 0, static_cast<int>(a.size()));
    return top;
}

int main() {
    vector<int> data = {9, 4, 7, 1, 8, 20, 15, 3};
    buildHeap(data);
    cout << "heap after build: ";
    for (int v : data) cout << v << " ";
    cout << endl;
    cout << "extracted in descending order:" << endl;
    while (!data.empty()) {
        cout << extractMax(data) << " ";
    }
    cout << endl;
    return 0;
}
