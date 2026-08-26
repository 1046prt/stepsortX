// sortsort · Kth Largest Element
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kth-largest-element

#include <bits/stdc++.h>
using namespace std;

int partition(vector<int>& a, int left, int right) {
    swap(a[left + rand() % (right - left + 1)], a[right]);
    int pivot = a[right];
    int store = left;
    for (int i = left; i < right; i++) {
        if (a[i] < pivot) swap(a[i], a[store++]);
    }
    swap(a[store], a[right]);
    return store;
}

int quickSelect(vector<int>& a, int k) {
    // Returns kth largest by partitioning toward index n - k.
    int left = 0, right = (int)a.size() - 1;
    int target = (int)a.size() - k;
    while (true) {
        int p = partition(a, left, right);
        if (p == target) return a[p];
        if (p < target) left = p + 1;
        else right = p - 1;
    }
}

int main() {
    vector<int> nums = {3, 2, 1, 5, 6, 4};
    vector<int> work = nums;
    cout << "k=2 -> " << quickSelect(work, 2) << endl;
    work = nums;
    cout << "k=4 -> " << quickSelect(work, 4) << endl;
}
