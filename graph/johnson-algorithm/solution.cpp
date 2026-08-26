// Stepsort · Johnson's Algorithm
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/johnson-algorithm

#include <bits/stdc++.h>
using namespace std;

const long long INF = LLONG_MAX / 4;
typedef pair<int, long long> Arc;               // entry: (to, weight)
struct WeightedArc { int u, v; long long w; };  // directed input edge

vector<long long> bellmanFord(int n, const vector<WeightedArc>& edges, int source) {
    vector<long long> dist(n, INF);
    dist[source] = 0;
    for (int round = 0; round < n; round++) {  // at most n rounds of relaxation
        bool changed = false;
        for (const WeightedArc& e : edges) {
            if (dist[e.u] < INF && dist[e.u] + e.w < dist[e.v]) {
                dist[e.v] = dist[e.u] + e.w;
                changed = true;
            }
        }
        if (!changed) break;
    }
    for (const WeightedArc& e : edges)
        if (dist[e.u] < INF && dist[e.u] + e.w < dist[e.v])
            return vector<long long>();  // negative cycle detected
    return dist;
}

vector<long long> dijkstra(int n, const vector<vector<Arc>>& adj, int source) {
    vector<long long> dist(n, INF);
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    dist[source] = 0;
    pq.push(make_pair(0LL, source));
    while (!pq.empty()) {
        pair<long long, int> top = pq.top();
        pq.pop();
        if (top.first > dist[top.second]) continue;
        for (const Arc& e : adj[top.second]) {
            long long candidate = top.first + e.second;
            if (candidate < dist[e.first]) {
                dist[e.first] = candidate;
                pq.push(make_pair(candidate, e.first));
            }
        }
    }
    return dist;
}

vector<vector<long long>> johnson(int n, const vector<WeightedArc>& edges) {
    // Virtual vertex n with 0-weight arcs feeds Bellman-Ford potentials
    vector<WeightedArc> extended = edges;
    for (int v = 0; v < n; v++) extended.push_back({n, v, 0});
    vector<long long> h = bellmanFord(n + 1, extended, n);
    if (h.empty()) return vector<vector<long long>>();
    vector<vector<Arc>> adj(n);
    for (const WeightedArc& e : edges)
        adj[e.u].push_back(make_pair(e.v, e.w + h[e.u] - h[e.v]));  // reweighted >= 0
    vector<vector<long long>> result(n, vector<long long>(n, INF));
    for (int s = 0; s < n; s++) {
        vector<long long> dist = dijkstra(n, adj, s);
        for (int v = 0; v < n; v++)
            if (dist[v] < INF) result[s][v] = dist[v] - h[s] + h[v];  // undo reweighting
    }
    return result;
}

int main() {
    int n = 5;
    vector<WeightedArc> edges = {
        {0, 1, 3}, {0, 2, 8}, {0, 4, -4},
        {1, 3, 1}, {1, 4, 7},
        {2, 1, 4},
        {3, 0, 2}, {3, 2, -5},
        {4, 3, 6},
    };
    vector<vector<long long>> dist = johnson(n, edges);
    if (dist.empty()) {
        cout << "Graph contains a negative weight cycle" << endl;
        return 0;
    }
    cout << "All-pairs shortest path distances:" << endl;
    for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
            if (v > 0) cout << " ";
            if (dist[u][v] >= INF) cout << "inf";
            else cout << dist[u][v];
        }
        cout << endl;
    }
    return 0;
}
