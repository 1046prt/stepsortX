// sortsort · Bipartite Check
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-check

#include <bits/stdc++.h>
using namespace std;

// BFS 2-coloring from one start vertex; false means a conflict was found
bool bfsColor(const vector<vector<int>>& adj, int start, vector<int>& color) {
    color[start] = 0;
    queue<int> q;
    q.push(start);
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (int v : adj[u]) {
            if (color[v] == -1) {
                color[v] = color[u] ^ 1;  // opposite color of the neighbor
                q.push(v);
            } else if (color[v] == color[u]) {
                return false;  // odd cycle makes 2-coloring impossible
            }
        }
    }
    return true;
}

bool isBipartite(int n, const vector<pair<int, int>>& edges, vector<int>& color) {
    vector<vector<int>> adj(n);
    for (const pair<int, int>& e : edges) {
        adj[e.first].push_back(e.second);
        adj[e.second].push_back(e.first);
    }
    color.assign(n, -1);
    for (int v = 0; v < n; v++) {
        if (color[v] == -1 && !bfsColor(adj, v, color)) return false;
    }
    return true;
}

int main() {
    int n = 5;
    vector<pair<int, int>> edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}};
    vector<int> color;
    if (!isBipartite(n, edges, color)) {
        cout << "Graph is NOT bipartite" << endl;
        return 0;
    }
    cout << "Graph is bipartite" << endl;
    cout << "Set A:";
    for (int v = 0; v < n; v++) if (color[v] == 0) cout << " " << v;
    cout << endl << "Set B:";
    for (int v = 0; v < n; v++) if (color[v] == 1) cout << " " << v;
    cout << endl;
    return 0;
}
