// Stepsort · 0-1 BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/zero-one-bfs

#include <bits/stdc++.h>
using namespace std;

vector<int> zeroOneBFS(int n, const vector<tuple<int,int,int>>& edges, int src = 0) {
    vector<vector<pair<int,int>>> adj(n);
    for (auto& [u, v, w] : edges) {
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }
    vector<int> dist(n, INT_MAX);
    dist[src] = 0;
    deque<int> dq{src};
    while (!dq.empty()) {
        int u = dq.front(); dq.pop_front();
        for (auto& [v, w] : adj[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                w == 0 ? dq.push_front(v) : dq.push_back(v);
            }
        }
    }
    return dist;
}

int main() {
    vector<tuple<int,int,int>> edges =
        {{0,1,0},{0,2,1},{1,3,0},{2,3,0},{2,4,1},{3,5,1},{4,5,0}};
    for (int d : zeroOneBFS(6, edges)) cout << d << " ";
    cout << endl;
    return 0;
}
