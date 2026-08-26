// Stepsort · Minimum Vertex Cut
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-vertex-cut

int minVertexCut(int n, vector<pair<int,int>>& edges, int s, int t) {
    int N = 2 * n;
    vector<vector<int>> cap(N, vector<int>(N, 0));
    for (auto [u,v] : edges) cap[u][v] = INT_MAX;
    for (int i = 0; i < n; i++) cap[i][i+n] = 1;
    cap[s+n][s] = INT_MAX; cap[t][t+n] = INT_MAX;
    int flow = 0;
    while (true) {
        vector<int> parent(N, -1);
        queue<int> q; q.push(s+n); parent[s+n] = s+n;
        while (!q.empty() && parent[t+n] == -1) {
            int u = q.front(); q.pop();
            for (int v = 0; v < N; v++)
                if (parent[v] == -1 && cap[u][v] > 0) parent[v] = u, q.push(v);
        }
        if (parent[t+n] == -1) break;
        int f = INT_MAX;
        for (int v = t+n; v != s+n; v = parent[v]) f = min(f, cap[parent[v]][v]);
        for (int v = t+n; v != s+n; v = parent[v]) cap[parent[v]][v] -= f, cap[v][parent[v]] += f;
        flow += f;
    }
    return flow;
}
