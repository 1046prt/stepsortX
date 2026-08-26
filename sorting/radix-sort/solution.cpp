// sortsort · Radix Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/radix-sort

#include <bits/stdc++.h>
using namespace std;

// Stable counting sort keyed by the digit at place value exp.
void countingSortByDigit(vector<int>& arr, int exp) {
    int n = (int)arr.size();
    vector<int> output(n);
    vector<int> counts(10, 0);
    for (int value : arr) counts[(value / exp) % 10]++;
    for (int digit = 1; digit < 10; digit++) counts[digit] += counts[digit - 1];
    for (int i = n - 1; i >= 0; i--) {
        int digit = (arr[i] / exp) % 10;
        counts[digit]--;
        output[counts[digit]] = arr[i];
    }
    arr = output;
}

// LSD passes, one per decimal digit of the maximum value.
void radixSort(vector<int>& arr) {
    if (arr.empty()) return;
    int maxValue = *max_element(arr.begin(), arr.end());
    for (int exp = 1; maxValue / exp > 0; exp *= 10) {
        countingSortByDigit(arr, exp);
    }
}

int main() {
    vector<int> data = {170, 45, 75, 90, 802, 24, 2, 66};
    radixSort(data);
    cout << "sorted:";
    for (int x : data) cout << " " << x;
    cout << endl;
    return 0;
}
