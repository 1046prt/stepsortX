// sortsort · BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bfs

#include <bits/stdc++.h>
using namespace std;

vector<int> bfs(const vector<vector<int>>& graph, int source) {
    vector<bool> visited(graph.size(), false);
    vector<int> order;
    queue<int> q;
    q.push(source);
    visited[source] = true;

    while (!q.empty()) {
        int node = q.front();
        q.pop();
        order.push_back(node);
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                q.push(neighbor);
            }
        }
    }
    return order;
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

    vector<int> order = bfs(graph, 0);

    cout << "BFS visit order from vertex 0:";
    for (int node : order) cout << " " << node;
    cout << endl;
    return 0;
}
