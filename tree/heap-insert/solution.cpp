// Stepsort · Heap Insert
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-insert

// Max-heap insert on an array with sift-up
#include <bits/stdc++.h>
using namespace std;

// move the value at i up while it is larger than its parent
void siftUp(vector<int>& a, int i) {
    while (i > 0) {
        int parent = (i - 1) / 2;
        if (a[i] <= a[parent]) break;
        swap(a[i], a[parent]);
        i = parent;
    }
}

// append at the end, then sift up to restore heap order
void insert(vector<int>& a, int value) {
    a.push_back(value);
    siftUp(a, static_cast<int>(a.size()) - 1);
}

void printArray(const vector<int>& a) {
    cout << "[";
    for (size_t i = 0; i < a.size(); ++i) {
        cout << a[i];
        if (i + 1 < a.size()) cout << ", ";
    }
    cout << "]";
}

int main() {
    vector<int> a;
    int values[] = {15, 12, 20, 8, 25, 18, 30, 5};
    for (int v : values) {
        insert(a, v);
        cout << "inserted " << v << " -> array: ";
        printArray(a);
        cout << endl;
    }
    cout << "max element sits at index 0: " << a[0] << endl;
    return 0;
}
