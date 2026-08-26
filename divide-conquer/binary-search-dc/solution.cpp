// Stepsort · Binary Search (D&C)
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-dc

#include <bits/stdc++.h>
using namespace std;

// recursive divide-and-conquer search over a sorted array
int binarySearch(const vector<int>& arr, int target, int low, int high) {
    if (low > high) return -1;
    int mid = low + (high - low) / 2;
    if (arr[mid] == target) return mid;
    if (arr[mid] < target) return binarySearch(arr, target, mid + 1, high);
    return binarySearch(arr, target, low, mid - 1);
}

int main() {
    vector<int> data = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
    cout << "sorted data:";
    for (int value : data) cout << " " << value;
    cout << endl;
    for (int target : {23, 2, 91, 40}) {
        int index = binarySearch(data, target, 0, (int)data.size() - 1);
        if (index == -1)
            cout << target << " not found" << endl;
        else
            cout << target << " found at index " << index << endl;
    }
    return 0;
}
