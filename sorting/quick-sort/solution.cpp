// Stepsort · Quick Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/quick-sort

#include <bits/stdc++.h>
using namespace std;

// Lomuto scheme: pivot is the last element of the range.
int partition(vector<int>& arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

void quickSort(vector<int>& arr, int low, int high) {
    if (low < high) {
        int p = partition(arr, low, high);
        quickSort(arr, low, p - 1);
        quickSort(arr, p + 1, high);
    }
}

int main() {
    vector<int> data = {10, 7, 8, 9, 1, 5};
    quickSort(data, 0, (int)data.size() - 1);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
