// Stepsort · Edmonds-Karp
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-edmonds-karp

#include <bits/stdc++.h>
using namespace std;

int edmondsKarp(int n, vector<vector<int>> residual, int s, int t) {
    int maxFlow = 0;
    while (true) {
        // BFS for a shortest augmenting path, recording parents
        vector<int> parent(n, -1);
        parent[s] = s;
        queue<int> q;
        q.push(s);
        while (!q.empty() && parent[t] == -1) {
            int u = q.front();
            q.pop();
            for (int v = 0; v < n; ++v) {
                if (parent[v] == -1 && residual[u][v] > 0) {
                    parent[v] = u;
                    q.push(v);
                }
            }
        }
        if (parent[t] == -1) break;

        int bottleneck = INT_MAX;
        for (int v = t; v != s; v = parent[v]) {
            bottleneck = min(bottleneck, residual[parent[v]][v]);
        }
        for (int v = t; v != s; v = parent[v]) {
            residual[parent[v]][v] -= bottleneck;
            residual[v][parent[v]] += bottleneck;
        }
        maxFlow += bottleneck;
    }
    return maxFlow;
}

int main() {
    int n = 6;
    vector<vector<int>> capacity(n, vector<int>(n, 0));
    vector<vector<int>> edges = {
        {0, 1, 16}, {0, 2, 13},
        {1, 3, 12},
        {2, 1, 4}, {2, 4, 14},
        {3, 2, 9}, {3, 5, 20},
        {4, 3, 7}, {4, 5, 4}
    };
    for (const auto& e : edges) capacity[e[0]][e[1]] = e[2];
    cout << "Max flow: " << edmondsKarp(n, capacity, 0, 5) << endl;
    return 0;
}
