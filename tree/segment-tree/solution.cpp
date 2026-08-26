// sortsort · Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/segment-tree

// Segment tree with range-sum query and point update
#include <bits/stdc++.h>
using namespace std;

struct SegmentTree {
    int n;
    vector<int> tree;

    explicit SegmentTree(const vector<int>& values)
        : n(static_cast<int>(values.size())), tree(4 * n) {
        build(values, 1, 0, n - 1);
    }

    void build(const vector<int>& values, int node, int lo, int hi) {
        if (lo == hi) {
            tree[node] = values[lo];
            return;
        }
        int mid = (lo + hi) / 2;
        build(values, 2 * node, lo, mid);
        build(values, 2 * node + 1, mid + 1, hi);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    // sum over the intersection of [l, r] with segment [lo, hi]
    long long query(int node, int lo, int hi, int l, int r) const {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return tree[node];
        int mid = (lo + hi) / 2;
        long long left = query(2 * node, lo, mid, l, r);
        long long right = query(2 * node + 1, mid + 1, hi, l, r);
        return left + right;
    }

    long long rangeSum(int l, int r) const { return query(1, 0, n - 1, l, r); }

    void update(int node, int lo, int hi, int pos, int value) {
        if (lo == hi) {
            tree[node] = value;
            return;
        }
        int mid = (lo + hi) / 2;
        if (pos <= mid) update(2 * node, lo, mid, pos, value);
        else update(2 * node + 1, mid + 1, hi, pos, value);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    void pointUpdate(int pos, int value) { update(1, 0, n - 1, pos, value); }
};

int main() {
    vector<int> arr = {2, 5, 1, 4, 9, 3};
    SegmentTree st(arr);
    cout << "sum arr[1..3]: " << st.rangeSum(1, 3) << endl;
    cout << "sum arr[0..5]: " << st.rangeSum(0, 5) << endl;
    st.pointUpdate(2, 10);  // arr[2] = 10
    cout << "after setting arr[2] = 10" << endl;
    cout << "sum arr[1..3]: " << st.rangeSum(1, 3) << endl;
    cout << "sum arr[0..5]: " << st.rangeSum(0, 5) << endl;
    return 0;
}
