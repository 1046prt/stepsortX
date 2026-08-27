// Stepsort · Dutch National Flag
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dutch-national-flag

#include <bits/stdc++.h>
using namespace std;

// Dutch National Flag: partitions array around pivot into [< | == | >]
pair<int,int> dutchNationalFlag(vector<int>& arr) {
    int pivot = arr[arr.size() / 2];
    int low = 0, mid = 0, high = (int)arr.size() - 1;
    while (mid <= high) {
        if (arr[mid] < pivot) {
            swap(arr[low], arr[mid]);
            low++; mid++;
        } else if (arr[mid] == pivot) {
            mid++;
        } else {
            swap(arr[mid], arr[high]);
            high--;
        }
    }
    return {low, mid}; // [<low, low..mid, mid+high>]
}

int main() {
    vector<int> data = {2, 0, 1, 2, 1, 0};
    auto [lo, hi] = dutchNationalFlag(data);
    cout << "partitioned:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
