// sortsort · Tim Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tim-sort

#include <bits/stdc++.h>
using namespace std;

const int RUN = 32;

// Sort arr[left..right] in place.
void insertionSort(vector<int>& arr, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= left && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

// Combine adjacent sorted runs arr[left..mid] and arr[mid+1..right].
void merge(vector<int>& arr, int left, int mid, int right) {
    vector<int> merged;
    merged.reserve(right - left + 1);
    int i = left;
    int j = mid + 1;
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) merged.push_back(arr[i++]);
        else merged.push_back(arr[j++]);
    }
    while (i <= mid) merged.push_back(arr[i++]);
    while (j <= right) merged.push_back(arr[j++]);
    for (int k = 0; k < (int)merged.size(); k++) arr[left + k] = merged[k];
}

// Insertion-sort fixed-size runs, then merge runs bottom-up.
void timSort(vector<int>& arr) {
    int n = (int)arr.size();
    for (int start = 0; start < n; start += RUN) {
        insertionSort(arr, start, min(start + RUN - 1, n - 1));
    }
    for (int size = RUN; size < n; size *= 2) {
        for (int left = 0; left < n; left += 2 * size) {
            int mid = min(left + size - 1, n - 1);
            int right = min(left + 2 * size - 1, n - 1);
            if (mid < right) merge(arr, left, mid, right);
        }
    }
}

int main() {
    vector<int> data = {3, 15, 8, 90, 42, 7, 61, 27, 4, 88, 16, 55};
    timSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
