// sortsort · DAG Shortest Path
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-shortest-path

#include <bits/stdc++.h>
using namespace std;

int main() {
    int n = 6;
    vector<tuple<int,int,int>> edges =
        {{0,1,3},{0,2,2},{1,3,4},{2,3,-2},{2,4,5},{3,5,1},{4,5,-1}};
    vector<vector<pair<int,int>>> adj(n);
    vector<int> indeg(n, 0);
    for (auto& [u, v, w] : edges) { adj[u].push_back({v, w}); indeg[v]++; }

    queue<int> q;
    for (int i = 0; i < n; i++) if (indeg[i] == 0) q.push(i);
    vector<int> topo;
    while (!q.empty()) {
        int u = q.front(); q.pop();
        topo.push_back(u);
        for (auto& [v, w] : adj[u]) if (--indeg[v] == 0) q.push(v);
    }

    vector<long long> dist(n, LLONG_MAX);
    dist[0] = 0;
    for (int u : topo) {
        if (dist[u] == LLONG_MAX) continue;
        for (auto& [v, w] : adj[u]) dist[v] = min(dist[v], dist[u] + w);
    }
    for (long long d : dist) cout << d << " ";
    cout << endl;   // 0 3 2 4 7 5
}
