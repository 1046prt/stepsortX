// sortsort · Minimum Cut Reconstruction
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-cut-reconstruction

pair<int,vector<pair<int,int>>> minCutReconstruction(vector<vector<int>> cap, int s, int t) {
    int n = cap.size(), flow = 0;
    vector<int> parent(n);
    while (true) {
        fill(parent.begin(), parent.end(), -1);
        queue<int> q; q.push(s); parent[s] = s;
        while (!q.empty() && parent[t] == -1) {
            int u = q.front(); q.pop();
            for (int v = 0; v < n; v++)
                if (parent[v] == -1 && cap[u][v] > 0)
                    parent[v] = u, q.push(v);
        }
        if (parent[t] == -1) break;
        int f = INT_MAX;
        for (int v = t; v != s; v = parent[v]) f = min(f, cap[parent[v]][v]);
        for (int v = t; v != s; v = parent[v]) cap[parent[v]][v] -= f, cap[v][parent[v]] += f;
        flow += f;
    }
    vector<int> vis(n, 0); queue<int> q; q.push(s); vis[s] = 1;
    while (!q.empty()) { int u = q.front(); q.pop();
        for (int v = 0; v < n; v++) if (!vis[v] && cap[u][v] > 0) vis[v] = 1, q.push(v); }
    vector<pair<int,int>> cut;
    for (int u = 0; u < n; u++) if (vis[u])
        for (int v = 0; v < n; v++) if (!vis[v] && cap[u][v] == 0) cut.push_back({u,v});
    return {flow, cut};
}
