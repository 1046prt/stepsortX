// sortsort · Optimal Merge Pattern
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/optimal-merge

#include <bits/stdc++.h>
using namespace std;

long long optimalMergeCost(const vector<int>& fileSizes) {
    // always combine the two smallest files first
    priority_queue<long long, vector<long long>, greater<long long>> minHeap;
    for (int size : fileSizes) minHeap.push(size);

    long long totalCost = 0;
    while (minHeap.size() > 1) {
        long long first = minHeap.top(); minHeap.pop();
        long long second = minHeap.top(); minHeap.pop();
        long long cost = first + second;
        totalCost += cost;
        minHeap.push(cost);
    }
    return totalCost;
}

int main() {
    vector<int> sizes = {4, 3, 2, 6};
    cout << "Minimum merge cost: " << optimalMergeCost(sizes) << endl;
    return 0;
}
