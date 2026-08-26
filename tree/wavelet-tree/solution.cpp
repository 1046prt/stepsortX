// Stepsort · Wavelet Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/wavelet-tree

#include <bits/stdc++.h>
using namespace std;

struct WNode {
    int lo, hi;
    vector<int> bound = {0};
    WNode* left = nullptr;
    WNode* right = nullptr;
};

WNode* build(const vector<int>& seq, int lo, int hi) {
    WNode* node = new WNode();
    node->lo = lo;
    node->hi = hi;
    if (lo == hi || seq.empty()) return node;
    int mid = (lo + hi) / 2;
    vector<int> ls, rs;
    for (int v : seq) {
        if (v <= mid) ls.push_back(v);
        else rs.push_back(v);
        node->bound.push_back((int)ls.size());
    }
    node->left = build(ls, lo, mid);
    node->right = build(rs, mid + 1, hi);
    return node;
}

int rank_of(WNode* node, int c, int i) {
    while (node->lo != node->hi) {
        int mid = (node->lo + node->hi) / 2;
        if (c <= mid) { i = node->bound[i]; node = node->left; }
        else { i -= node->bound[i]; node = node->right; }
    }
    return i;
}

int main() {
    vector<int> arr = {3, 1, 4, 1, 5, 2};
    WNode* root = build(arr, 1, 5);
    cout << "rank(1, 6) = " << rank_of(root, 1, 6) << endl;
    cout << "rank(5, 6) = " << rank_of(root, 5, 6) << endl;
}
