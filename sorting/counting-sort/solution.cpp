// Stepsort · Counting Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/counting-sort

#include <bits/stdc++.h>
using namespace std;

// Tally occurrences of each value, assuming non-negative integers.
vector<int> countingSort(const vector<int>& arr) {
    if (arr.empty()) return {};
    int maxValue = *max_element(arr.begin(), arr.end());
    vector<int> counts(maxValue + 1, 0);
    for (int value : arr) counts[value]++;
    vector<int> result;
    result.reserve(arr.size());
    for (int value = 0; value <= maxValue; value++) {
        result.insert(result.end(), counts[value], value);
    }
    return result;
}

int main() {
    vector<int> data = {4, 2, 2, 8, 3, 3, 1};
    vector<int> sortedData = countingSort(data);
    cout << "sorted:";
    for (int x : sortedData) cout << " " << x;
    cout << endl;
    return 0;
}
