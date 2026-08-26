// sortsort · DAG Dynamic Programming
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-dp

vector<int> dagLongestPath(int n, vector<tuple<int,int,int>>& edges) {
    vector<vector<pair<int,int>>> adj(n);
    vector<int> indeg(n, 0);
    for (auto [u, v, w] : edges) { adj[u].push_back({v, w}); indeg[v]++; }
    queue<int> q;
    for (int i = 0; i < n; i++) if (indeg[i] == 0) q.push(i);
    vector<int> topo;
    while (!q.empty()) { int u = q.front(); q.pop(); topo.push_back(u);
        for (auto [v, w] : adj[u]) if (--indeg[v] == 0) q.push(v); }
    vector<int> dist(n, INT_MIN);
    for (int i = 0; i < n; i++) if (indeg[i] == 0) dist[i] = 0;
    for (int u : topo)
        for (auto [v, w] : adj[u])
            dist[v] = max(dist[v], dist[u] + w);
    return dist;
}
