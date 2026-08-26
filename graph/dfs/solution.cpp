// sortsort · DFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dfs

#include <bits/stdc++.h>
using namespace std;

void explore(const vector<vector<int>>& graph, int node,
             vector<bool>& visited, vector<int>& order) {
    visited[node] = true;
    order.push_back(node);
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            explore(graph, neighbor, visited, order);
        }
    }
}

int main() {
    // Undirected graph with 6 vertices (0..5)
    vector<vector<int>> graph = {
        {1, 2},
        {0, 3},
        {0, 3, 4},
        {1, 2, 5},
        {2},
        {3},
    };

    vector<bool> visited(graph.size(), false);
    vector<int> order;
    explore(graph, 0, visited, order);

    cout << "DFS visit order from vertex 0:";
    for (int node : order) cout << " " << node;
    cout << endl;
    return 0;
}
