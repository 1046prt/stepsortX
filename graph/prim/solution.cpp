// Stepsort · Prim's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prim

#include <bits/stdc++.h>
using namespace std;

pair<long long, vector<tuple<int, int, int>>> prim(
    int num_vertices, const vector<vector<pair<int, int>>>& graph) {
    // Min-heap of (weight, vertex, parent)
    typedef tuple<int, int, int> Item;
    priority_queue<Item, vector<Item>, greater<Item>> pq;
    vector<bool> visited(num_vertices, false);
    long long total_weight = 0;
    vector<tuple<int, int, int>> mst;

    pq.push({0, 0, -1});  // start at vertex 0 with virtual parent -1
    while (!pq.empty()) {
        auto [weight, node, parent] = pq.top();
        pq.pop();
        if (visited[node]) continue;  // stale queue entry
        visited[node] = true;
        total_weight += weight;
        if (parent != -1) mst.push_back({parent, node, weight});
        for (const auto& [w, neighbor] : graph[node]) {
            if (!visited[neighbor]) pq.push({w, neighbor, node});
        }
    }
    return {total_weight, mst};
}

int main() {
    int num_vertices = 5;
    vector<vector<pair<int, int>>> graph(num_vertices);
    vector<tuple<int, int, int>> edges = {
        {0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}
    };
    for (const auto& [u, v, w] : edges) {
        graph[u].push_back({w, v});
        graph[v].push_back({w, u});
    }

    auto [total_weight, mst] = prim(num_vertices, graph);

    cout << "Prim MST total weight: " << total_weight << endl;
    cout << "MST edges:" << endl;
    for (const auto& [u, v, w] : mst) {
        cout << "  " << u << " - " << v << " (weight " << w << ")" << endl;
    }
    return 0;
}
