// sortsort · Topological Sort
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/topological-sort

#include <bits/stdc++.h>
using namespace std;

// Kahn BFS method: repeatedly take vertices whose indegree hits zero
vector<int> topologicalSort(int V, const vector<vector<int>>& adj) {
    vector<int> indegree(V, 0);
    for (int u = 0; u < V; u++)
        for (int v : adj[u])
            indegree[v]++;

    queue<int> ready;
    for (int u = 0; u < V; u++)
        if (indegree[u] == 0) ready.push(u);

    vector<int> order;
    while (!ready.empty()) {
        int u = ready.front();
        ready.pop();
        order.push_back(u);
        for (int v : adj[u])
            if (--indegree[v] == 0) ready.push(v);
    }
    return order;
}

int main() {
    int V = 6;
    vector<vector<int>> adj(V);
    int edges[][2] = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
    for (auto& e : edges) adj[e[0]].push_back(e[1]);

    vector<int> order = topologicalSort(V, adj);
    cout << "Topological order:";
    for (int u : order) cout << " " << u;
    cout << endl;
    cout << "Valid DAG ordering: " << (order.size() == (size_t)V) << endl;
    return 0;
}
