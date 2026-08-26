// Stepsort · General Graph Matching
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/general-matching

int blossomMatch(int n, vector<vector<int>>& adj) {
    vector<int> match(n, -1), label(n), parent(n), base(n);
    iota(base.begin(), base.end(), 0);
    auto lca = [&](int a, int b) {
        vector<bool> seen(n, false);
        for (int x = a; ; x = parent[match[base[x]]]) {
            x = base[x]; seen[x] = true;
            if (match[x] == -1) break;
        }
        for (int y = b; ; y = parent[match[base[y]]]) {
            y = base[y];
            if (seen[y]) return y;
        }
    };
    auto markPath = [&](int v, int b, int child) {
        for (; base[v] != b; v = parent[match[v]]) {
            label[base[v]] = label[base[match[v]]] = 1;
            parent[v] = child; child = match[v];
        }
    };
    int result = 0;
    for (int root = 0; root < n; root++) {
        if (match[root] != -1) continue;
        fill(label.begin(), label.end(), 0);
        iota(base.begin(), base.end(), 0);
        queue<int> q; q.push(root); label[root] = 1;
        bool found = false;
        while (!q.empty() && !found) {
            int u = q.front(); q.pop();
            for (int v : adj[u]) {
                if (label[v] == 0) {
                    label[v] = 2; parent[v] = u;
                    if (match[v] == -1) {
                        for (int x = v; x != -1; ) {
                            int px = parent[x], pmx = match[px];
                            match[x] = px; match[px] = x; x = pmx;
                        }
                        found = true; break;
                    }
                    label[match[v]] = 1; q.push(match[v]);
                } else if (label[v] == 1) {
                    int b = lca(u, v);
                    markPath(u, b, v); markPath(v, b, u);
                }
            }
        }
        if (found) result++;
    }
    return result;
}
