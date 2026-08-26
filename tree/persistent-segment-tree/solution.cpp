// Stepsort · Persistent Segment Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/persistent-segment-tree

#include <bits/stdc++.h>
using namespace std;

struct Node {
    long long sum;
    int left, right;
};

vector<Node> st;

int build(const vector<int>& arr, int l, int r) {
    int id = (int)st.size();
    st.push_back({0, -1, -1});
    if (l == r) { st[id].sum = arr[l]; return id; }
    int mid = (l + r) / 2;
    st[id].left = build(arr, l, mid);
    st[id].right = build(arr, mid + 1, r);
    st[id].sum = st[st[id].left].sum + st[st[id].right].sum;
    return id;
}

int update(int prev, int l, int r, int idx, int val) {
    Node copy = st[prev];
    int id = (int)st.size();
    st.push_back(copy);
    if (l == r) { st[id].sum = val; return id; }
    int mid = (l + r) / 2;
    if (idx <= mid) st[id].left = update(st[id].left, l, mid, idx, val);
    else st[id].right = update(st[id].right, mid + 1, r, idx, val);
    st[id].sum = st[st[id].left].sum + st[st[id].right].sum;
    return id;
}

long long query(int node, int l, int r, int lo, int hi) {
    if (hi < l || r < lo) return 0;
    if (lo <= l && r <= hi) return st[node].sum;
    int mid = (l + r) / 2;
    return query(st[node].left, l, mid, lo, hi) +
           query(st[node].right, mid + 1, r, lo, hi);
}

int main() {
    vector<int> arr = {1, 3, 5, 7};
    vector<int> roots;
    roots.push_back(build(arr, 0, 3));
    roots.push_back(update(roots[0], 0, 3, 2, 9));
    cout << "v0 sum[0..1] = " << query(roots[0], 0, 3, 0, 1) << endl;
    cout << "v1 sum[0..1] = " << query(roots[1], 0, 3, 0, 1) << endl;
}
