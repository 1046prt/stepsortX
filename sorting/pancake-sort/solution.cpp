// Stepsort · Pancake Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pancake-sort

#include <bits/stdc++.h>
using namespace std;

// Reverse the prefix arr[0..k] in place.
void flip(vector<int>& arr, int k) {
    int left = 0, right = k;
    while (left < right) {
        swap(arr[left], arr[right]);
        left++;
        right--;
    }
}

int findMaxIndex(const vector<int>& arr, int limit) {
    int best = 0;
    for (int i = 1; i < limit; i++)
        if (arr[i] > arr[best]) best = i;
    return best;
}

void pancakeSort(vector<int>& arr) {
    // Max of the unsorted prefix to the front, then flipped into place.
    for (int size = (int)arr.size(); size > 1; size--) {
        int maxIdx = findMaxIndex(arr, size);
        if (maxIdx == size - 1) continue;
        if (maxIdx != 0) flip(arr, maxIdx);
        flip(arr, size - 1);
    }
}

int main() {
    vector<int> data = {6, 2, 9, 1, 5, 8};
    pancakeSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
