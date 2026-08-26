// Stepsort · Bucket Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bucket-sort

#include <bits/stdc++.h>
using namespace std;

// Standard insertion sort applied within one bucket.
void insertionSort(vector<double>& bucket) {
    for (int i = 1; i < (int)bucket.size(); i++) {
        double key = bucket[i];
        int j = i - 1;
        while (j >= 0 && bucket[j] > key) {
            bucket[j + 1] = bucket[j];
            j--;
        }
        bucket[j + 1] = key;
    }
}

// Scatter values in [0, 1) across n buckets, sort each, concatenate.
vector<double> bucketSort(const vector<double>& arr) {
    int n = (int)arr.size();
    vector<vector<double>> buckets(n);
    for (double value : arr) {
        buckets[min((int)(value * n), n - 1)].push_back(value);
    }
    vector<double> result;
    result.reserve(n);
    for (vector<double>& bucket : buckets) {
        insertionSort(bucket);
        result.insert(result.end(), bucket.begin(), bucket.end());
    }
    return result;
}

int main() {
    vector<double> data = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51};
    vector<double> sortedData = bucketSort(data);
    cout << "sorted:";
    cout << fixed << setprecision(2);
    for (double x : sortedData) cout << " " << x;
    cout << endl;
    return 0;
}
