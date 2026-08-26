// Stepsort · Karger's Min Cut
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-karger-min-cut

#include <bits/stdc++.h>
using namespace std;

mt19937 rng(42);

int findRoot(vector<int>& parent, int x) {
    while (parent[x] != x) {
        parent[x] = parent[parent[x]];
        x = parent[x];
    }
    return x;
}

// One contraction trial; assumes a connected graph.
int minCutOnce(int vertexCount, vector<pair<int, int>> edges) {
    vector<int> parent(vertexCount);
    iota(parent.begin(), parent.end(), 0);
    int components = vertexCount;
    while (components > 2) {
        uniform_int_distribution<size_t> pick(0, edges.size() - 1);
        size_t index = pick(rng);
        int u = edges[index].first;
        int v = edges[index].second;
        edges.erase(edges.begin() + index);
        int rootU = findRoot(parent, u);
        int rootV = findRoot(parent, v);
        if (rootU == rootV) continue;  // self-loop: already contracted
        parent[rootV] = rootU;
        components--;
    }
    int cut = 0;
    for (const auto& edge : edges)
        if (findRoot(parent, edge.first) != findRoot(parent, edge.second))
            cut++;
    return cut;
}

int main() {
    int vertexCount = 4;
    vector<pair<int, int>> edges = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};
    const int trials = 200;
    int best = INT_MAX;
    for (int t = 0; t < trials; t++)
        best = min(best, minCutOnce(vertexCount, edges));
    cout << "minimum cut found over " << trials << " trials: " << best << endl;
    return 0;
}
