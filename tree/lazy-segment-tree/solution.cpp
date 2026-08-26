// Stepsort · Lazy Propagation Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lazy-segment-tree

#include <bits/stdc++.h>
using namespace std;

struct LazySegTree {
    int n;
    vector<long long> sum, lazy;
    LazySegTree(const vector<long long>& a) : n(a.size()), sum(4*n), lazy(4*n) {
        build(1, 0, n-1, a);
    }
    void build(int node, int l, int r, const vector<long long>& a) {
        if (l == r) { sum[node] = a[l]; return; }
        int m = (l + r) / 2;
        build(2*node, l, m, a);
        build(2*node+1, m+1, r, a);
        sum[node] = sum[2*node] + sum[2*node+1];
    }
    void apply(int node, int l, int r, long long v) {
        sum[node] += v * (r - l + 1);
        lazy[node] += v;
    }
    void push(int node, int l, int r) {
        if (lazy[node]) {
            int m = (l + r) / 2;
            apply(2*node, l, m, lazy[node]);
            apply(2*node+1, m+1, r, lazy[node]);
            lazy[node] = 0;
        }
    }
    void update(int node, int l, int r, int ql, int qr, long long v) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) { apply(node, l, r, v); return; }
        push(node, l, r);
        int m = (l + r) / 2;
        update(2*node, l, m, ql, qr, v);
        update(2*node+1, m+1, r, ql, qr, v);
        sum[node] = sum[2*node] + sum[2*node+1];
    }
    long long query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        push(node, l, r);
        if (ql <= l && r <= qr) return sum[node];
        int m = (l + r) / 2;
        return query(2*node, l, m, ql, qr) + query(2*node+1, m+1, r, ql, qr);
    }
};

int main() {
    LazySegTree st({1, 3, 5, 7, 9, 11});
    st.update(1, 0, 5, 1, 3, 5);
    cout << st.query(1, 0, 5, 1, 3) << endl;   // 36
}
