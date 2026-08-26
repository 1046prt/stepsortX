// Stepsort · Merge Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-sort

#include <bits/stdc++.h>
using namespace std;

// Combine the two sorted halves arr[left..mid] and arr[mid+1..right].
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

void mergeSort(vector<int>& arr, int left, int right) {
    if (left >= right) return;
    int mid = left + (right - left) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}

int main() {
    vector<int> data = {38, 27, 43, 3, 9, 82, 10};
    mergeSort(data, 0, (int)data.size() - 1);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
