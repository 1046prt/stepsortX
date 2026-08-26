// Stepsort · Kruskal's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kruskal

#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int u, v, w;
};

struct UnionFind {
    vector<int> parent;

    UnionFind(int size) : parent(size) {
        iota(parent.begin(), parent.end(), 0);
    }

    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];  // path compression
            x = parent[x];
        }
        return x;
    }

    bool unite(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootA] = rootB;
        return true;
    }
};

int main() {
    int num_vertices = 4;
    // Undirected weighted graph with 4 vertices (0..3)
    vector<Edge> edges = {
        {0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 2}, {2, 3, 5}
    };
    sort(edges.begin(), edges.end(),
         [](const Edge& a, const Edge& b) { return a.w < b.w; });

    UnionFind uf(num_vertices);
    long long total_weight = 0;
    vector<Edge> chosen;
    for (const Edge& e : edges) {
        if (uf.unite(e.u, e.v)) {  // skip edges that would form a cycle
            total_weight += e.w;
            chosen.push_back(e);
        }
    }

    cout << "Kruskal MST total weight: " << total_weight << endl;
    cout << "Chosen edges:" << endl;
    for (const Edge& e : chosen) {
        cout << "  " << e.u << " - " << e.v << " (weight " << e.w << ")" << endl;
    }
    return 0;
}
