// sortsort · Tree Diameter
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-diameter

#include <bits/stdc++.h>
using namespace std;

struct BFSResult {
    int far;
    int maxDist;
    vector<int> parent;
};

BFSResult bfsFarthest(const vector<vector<int>>& adj, int src) {
    vector<int> dist(adj.size(), -1), parent(adj.size(), -1);
    queue<int> q;
    q.push(src);
    dist[src] = 0;
    int far = src;
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (int v : adj[u]) {
            if (dist[v] == -1) {
                dist[v] = dist[u] + 1;
                parent[v] = u;
                if (dist[v] > dist[far]) far = v;
                q.push(v);
            }
        }
    }
    return {far, dist[far], parent};
}

int main() {
    int n = 7;
    vector<vector<int>> adj(n);
    auto addEdge = [&](int u, int v) { adj[u].push_back(v); adj[v].push_back(u); };
    addEdge(0, 1); addEdge(0, 2); addEdge(2, 3);
    addEdge(2, 4); addEdge(4, 5); addEdge(1, 6);

    BFSResult first = bfsFarthest(adj, 0);       // pass 1: any start
    BFSResult second = bfsFarthest(adj, first.far); // pass 2: from endpoint

    cout << "Diameter length: " << second.maxDist << endl;
    cout << "Endpoints: " << first.far << " and " << second.far << endl;

    vector<int> path;                            // rebuild via parents
    for (int cur = second.far; cur != -1; cur = second.parent[cur])
        path.push_back(cur);
    reverse(path.begin(), path.end());

    cout << "Path:";
    for (int x : path) cout << ' ' << x;
    cout << endl;
    return 0;
}
