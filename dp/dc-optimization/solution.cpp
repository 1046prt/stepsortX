// Stepsort · Divide & Conquer Optimization
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dc-optimization

#include <bits/stdc++.h>
using namespace std;

int G;

void solveLayer(int layer, const vector<long long>& prevRow,
                vector<long long>& cur,
                const vector<long long>& prefix,
                function<void(int,int,int,int)> rec) {
    // implemented via explicit stack-free recursion lambda below
}
void rec(int lo, int hi, int klo, int khi, int layer,
         const vector<long long>& prevRow, vector<long long>& cur,
         const vector<long long>& prefix) {
    if (lo > hi) return;
    int mid = (lo + hi) / 2;
    long long best = LLONG_MAX;
    int arg = max(klo, layer - 1);
    for (int k = max(klo, layer - 1); k <= min(khi, mid - 1); k++) {
        long long seg = prefix[mid + 1] - prefix[k];
        long long cand = prevRow[k] + seg * seg;
        if (cand < best) { best = cand; arg = k; }
    }
    cur[mid] = best;
    rec(lo, mid - 1, klo, arg, layer, prevRow, cur, prefix);
    rec(mid + 1, hi, arg, khi, layer, prevRow, cur, prefix);
}

int main() {
    vector<int> arr = {7, 2, 3, 9, 4, 1};
    int n = arr.size(), G = 3;
    vector<long long> prefix(n + 1, 0);
    for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];

    vector<long long> prevRow(n + 1, LLONG_MAX), cur(n + 1, LLONG_MAX);
    prevRow[0] = 0;
    for (int layer = 1; layer <= G; layer++) {
        fill(cur.begin(), cur.end(), LLONG_MAX);
        rec(layer, n - 1, layer - 1, n - 1, layer, prevRow, cur, prefix);
        prevRow = cur;
    }
    cout << "min cost: " << prevRow[n - 1] << endl;
}
