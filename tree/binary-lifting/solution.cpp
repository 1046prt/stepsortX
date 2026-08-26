// sortsort · Binary Lifting (LCA)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-lifting

#include <bits/stdc++.h>
using namespace std;

const int LOG = 4;

int main() {
    int n = 12;
    vector<int> parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
    vector<int> depth(n);
    vector<vector<int>> up(LOG, vector<int>(n, -1));
    up[0] = parent;
    for (int v = 1; v < n; v++) depth[v] = depth[parent[v]] + 1;
    for (int k = 1; k < LOG; k++)
        for (int v = 0; v < n; v++) {
            int prev = up[k - 1][v];
            up[k][v] = prev != -1 ? up[k - 1][prev] : -1;
        }

    int u = 11, v = 10;
    if (depth[u] < depth[v]) swap(u, v);
    int diff = depth[u] - depth[v];
    for (int k = 0; k < LOG; k++)
        if (diff & (1 << k)) u = up[k][u];
    if (u != v)
        for (int k = LOG - 1; k >= 0; k--)
            if (up[k][u] != up[k][v]) { u = up[k][u]; v = up[k][v]; }
    cout << "LCA = " << parent[u] << endl;   // 4
}
