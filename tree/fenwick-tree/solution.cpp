// Stepsort · Fenwick Tree (BIT)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fenwick-tree

// Fenwick (Binary Indexed) Tree: prefix sums with point add
#include <bits/stdc++.h>
using namespace std;

struct FenwickTree {
    int n;
    vector<long long> tree;

    explicit FenwickTree(const vector<int>& values)
        : n(static_cast<int>(values.size())), tree(n + 1, 0) {
        for (int i = 0; i < n; ++i) add(i + 1, values[i]);
    }

    // adds delta at 1-based index i, climbing to the next responsible cell
    void add(int i, long long delta) {
        while (i <= n) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    // sum of elements at indices 1..i, peeling off lowest set bits
    long long prefixSum(int i) const {
        long long total = 0;
        while (i > 0) {
            total += tree[i];
            i -= i & (-i);
        }
        return total;
    }

    // inclusive sum over indices l..r (1-based)
    long long rangeSum(int l, int r) const {
        return prefixSum(r) - prefixSum(l - 1);
    }
};

int main() {
    vector<int> arr = {3, 2, -1, 6, 5, 4};
    FenwickTree ft(arr);
    cout << "prefix sum to 3: " << ft.prefixSum(3) << endl;
    cout << "range sum 2..5: " << ft.rangeSum(2, 5) << endl;
    ft.add(4, 7);  // arr[3] += 7
    cout << "after adding 7 at index 4" << endl;
    cout << "prefix sum to 3: " << ft.prefixSum(3) << endl;
    cout << "prefix sum to 6: " << ft.prefixSum(6) << endl;
    cout << "range sum 2..5: " << ft.rangeSum(2, 5) << endl;
    return 0;
}
