// Stepsort · Merge Sort Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-sort-tree

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> tree;

void build(const vector<int>& arr, int node, int l, int r) {
    if (l == r) { tree[node] = {arr[l]}; return; }
    int mid = (l + r) / 2;
    build(arr, 2 * node, l, mid);
    build(arr, 2 * node + 1, mid + 1, r);
    const vector<int>& a = tree[2 * node];
    const vector<int>& b = tree[2 * node + 1];
    vector<int> merged;
    merged.reserve(a.size() + b.size());
    merge(a.begin(), a.end(), b.begin(), b.end(), back_inserter(merged));
    tree[node] = merged;
}

int query(int node, int l, int r, int lo, int hi, int x) {
    if (hi < l || r < lo) return 0;
    if (lo <= l && r <= hi)
        return (int)(upper_bound(tree[node].begin(), tree[node].end(), x) -
                     tree[node].begin());
    int mid = (l + r) / 2;
    return query(2 * node, l, mid, lo, hi, x) +
           query(2 * node + 1, mid + 1, r, lo, hi, x);
}

int main() {
    vector<int> arr = {5, 2, 6, 1, 3, 4, 7};
    int n = (int)arr.size();
    tree.assign(4 * n, {});
    build(arr, 1, 0, n - 1);
    cout << "count of values <= 4 in arr[1..5]: "
         << query(1, 0, n - 1, 1, 5, 4) << endl;
}
