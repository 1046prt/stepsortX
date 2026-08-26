// sortsort · Shell Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/shell-sort

#include <bits/stdc++.h>
using namespace std;

// Gapped insertion sorts with a gap that halves every pass.
void shellSort(vector<int>& arr) {
    int n = (int)arr.size();
    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j = i;
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }
}

int main() {
    vector<int> data = {12, 34, 54, 2, 3};
    shellSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
