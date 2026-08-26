// sortsort · Dijkstra's
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dijkstra

#include <bits/stdc++.h>
using namespace std;

const long long INF = LLONG_MAX;

vector<long long> dijkstra(int num_vertices,
                           const vector<vector<pair<int, int>>>& graph,
                           int source) {
    // Min-heap of (distance, vertex)
    typedef pair<long long, int> Item;
    priority_queue<Item, vector<Item>, greater<Item>> pq;
    vector<long long> dist(num_vertices, INF);

    dist[source] = 0;
    pq.push({0, source});

    while (!pq.empty()) {
        auto [d, node] = pq.top();
        pq.pop();
        if (d > dist[node]) continue;  // stale queue entry
        for (const auto& [weight, neighbor] : graph[node]) {
            if (d + weight < dist[neighbor]) {
                dist[neighbor] = d + weight;
                pq.push({dist[neighbor], neighbor});
            }
        }
    }
    return dist;
}

int main() {
    int num_vertices = 5;
    vector<vector<pair<int, int>>> graph(num_vertices);
    vector<tuple<int, int, int>> edges = {
        {0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 5}, {2, 3, 8}, {3, 4, 3}
    };
    for (const auto& [u, v, w] : edges) {
        graph[u].push_back({w, v});
        graph[v].push_back({w, u});
    }

    vector<long long> dist = dijkstra(num_vertices, graph, 0);

    cout << "Shortest distances from vertex 0:" << endl;
    for (int vertex = 0; vertex < num_vertices; vertex++) {
        if (dist[vertex] == INF) cout << "  vertex " << vertex << ": INF";
        else cout << "  vertex " << vertex << ": " << dist[vertex];
        cout << endl;
    }
    return 0;
}
