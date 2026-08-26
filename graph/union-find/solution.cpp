// sortsort · Union-Find (DSU)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/union-find

#include <bits/stdc++.h>
using namespace std;

struct DSU {
    vector<int> parent, rnk;
    DSU(int n) : parent(n), rnk(n, 0) { iota(parent.begin(), parent.end(), 0); }

    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];   // path compression
            x = parent[x];
        }
        return x;
    }

    bool unite(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rnk[ra] < rnk[rb]) swap(ra, rb);
        parent[rb] = ra;
        if (rnk[ra] == rnk[rb]) rnk[ra]++;
        return true;
    }
};

int main() {
    DSU uf(6);
    vector<pair<int,int>> ops = {{0,1},{2,3},{1,3},{4,5},{3,5}};
    for (auto& [a, b] : ops)
        cout << "union(" << a << "," << b << ") -> "
             << (uf.unite(a, b) ? "merged" : "already connected") << endl;
    return 0;
}
