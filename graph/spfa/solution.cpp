// Stepsort · SPFA (Shortest Path Faster)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/spfa

#include <bits/stdc++.h>
using namespace std;

vector<int> spfa(vector<vector<pair<int,int>>>& graph, int source, int n) {
    const int INF = 1e9;
    vector<int> dist(n, INF);
    vector<bool> inQueue(n, false);
    vector<int> enqueueCount(n, 0);
    dist[source] = 0;
    queue<int> q;
    q.push(source);
    inQueue[source] = true;
    enqueueCount[source] = 1;

    while (!q.empty()) {
        int u = q.front(); q.pop();
        inQueue[u] = false;
        for (auto& [v, w] : graph[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                if (!inQueue[v]) {
                    q.push(v);
                    inQueue[v] = true;
                    if (++enqueueCount[v] > n) return {}; // negative cycle
                }
            }
        }
    }
    return dist;
}

int main() {
    int n = 5;
    vector<vector<pair<int,int>>> g(n);
    for (auto& [u,v,w] : vector<tuple<int,int,int>>{{0,1,6},{0,2,4},{1,2,2},{1,3,5},{2,3,-3},{2,4,1},{3,4,2}})
        g[u].push_back({v, w});
    auto dist = spfa(g, 0, n);
    for (int i = 0; i < n; i++) cout << "dist[" << i << "]=" << dist[i] << " ";
    cout << endl;
    return 0;
}
