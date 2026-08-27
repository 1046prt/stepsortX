// Stepsort · Weighted Union-Find
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/union-find-weighted

#include <bits/stdc++.h>
using namespace std;

struct UnionFind {
    vector<int> parent, rank;
    int components;
    UnionFind(int n) : parent(n), rank(n, 0), components(n) {
        iota(parent.begin(), parent.end(), 0);
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }
    bool unite(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return false;
        if (rank[rx] < rank[ry]) swap(rx, ry);
        parent[ry] = rx;
        if (rank[rx] == rank[ry]) rank[rx]++;
        components--;
        return true;
    }
    bool connected(int x, int y) { return find(x) == find(y); }
};

int main() {
    UnionFind uf(6);
    uf.unite(0, 1); uf.unite(2, 3); uf.unite(1, 3);
    cout << "0 and 3 connected? " << (uf.connected(0, 3) ? "yes" : "no") << endl;
    cout << "0 and 4 connected? " << (uf.connected(0, 4) ? "yes" : "no") << endl;
    return 0;
}
