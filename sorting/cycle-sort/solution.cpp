// Stepsort · Cycle Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-sort

#include <bits/stdc++.h>
using namespace std;

int cycleSort(vector<int>& arr) {
    int n = (int)arr.size();
    int writes = 0;
    for (int start = 0; start < n - 1; start++) {
        int item = arr[start];
        int pos = start;
        for (int i = start + 1; i < n; i++)
            if (arr[i] < item) pos++;
        if (pos == start) continue;
        while (item == arr[pos]) pos++;
        swap(item, arr[pos]);
        writes++;
        while (pos != start) {
            pos = start;
            for (int i = start + 1; i < n; i++)
                if (arr[i] < item) pos++;
            while (item == arr[pos]) pos++;
            swap(item, arr[pos]);
            writes++;
        }
    }
    return writes;
}

int main() {
    vector<int> data = {4, 2, 5, 1, 3, 4};
    cout << "writes: " << cycleSort(data) << endl;
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
