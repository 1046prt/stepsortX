// Stepsort · Eulerian Path/Circuit
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eulerian-path

#include <bits/stdc++.h>
using namespace std;

// Hierholzer construction over an undirected multigraph
vector<int> eulerianPath(int n, const vector<pair<int, int>>& edges) {
    vector<vector<pair<int, int>>> adj(n);  // entries: (neighbor, edge id)
    vector<int> degree(n, 0);
    for (int i = 0; i < (int)edges.size(); i++) {
        adj[edges[i].first].push_back(make_pair(edges[i].second, i));
        adj[edges[i].second].push_back(make_pair(edges[i].first, i));
        degree[edges[i].first]++;
        degree[edges[i].second]++;
    }
    vector<int> odd;
    for (int v = 0; v < n; v++) {
        if (degree[v] % 2 == 1) odd.push_back(v);
    }
    vector<int> path;
    if (odd.size() != 0 && odd.size() != 2) return path;  // impossible
    int start = odd.empty() ? edges[0].first : odd[0];
    vector<bool> used(edges.size(), false);
    vector<int> stk = {start};
    while (!stk.empty()) {
        int v = stk.back();
        // Lazily discard already-used edges at this vertex
        while (!adj[v].empty() && used[adj[v].back().second]) adj[v].pop_back();
        if (adj[v].empty()) {
            path.push_back(v);
            stk.pop_back();
        } else {
            pair<int, int> e = adj[v].back();
            adj[v].pop_back();
            used[e.second] = true;
            stk.push_back(e.first);
        }
    }
    reverse(path.begin(), path.end());
    if ((int)path.size() != (int)edges.size() + 1) path.clear();  // disconnected edges
    return path;
}

int main() {
    vector<pair<int, int>> edges = {{0, 1}, {1, 2}, {2, 0}, {0, 3}, {3, 4}, {4, 0}};
    vector<int> path = eulerianPath(5, edges);
    if (path.empty()) {
        cout << "No Eulerian path exists" << endl;
    } else {
        cout << "Eulerian path:";
        for (int v : path) cout << " " << v;
        cout << endl;
    }
    return 0;
}
