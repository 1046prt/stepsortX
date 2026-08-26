// sortsort · Cocktail Shaker Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cocktail-sort

#include <bits/stdc++.h>
using namespace std;

// Bubble passes alternate direction, tightening both ends.
void cocktailSort(vector<int>& arr) {
    int start = 0;
    int end = (int)arr.size() - 1;
    bool swapped = true;
    while (swapped) {
        swapped = false;
        for (int i = start; i < end; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr[i], arr[i + 1]);
                swapped = true;
            }
        }
        if (!swapped) break;
        swapped = false;
        end--;
        for (int i = end; i > start; i--) {
            if (arr[i] < arr[i - 1]) {
                swap(arr[i], arr[i - 1]);
                swapped = true;
            }
        }
        start++;
    }
}

int main() {
    vector<int> data = {5, 1, 4, 2, 8, 0, 6};
    cocktailSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
