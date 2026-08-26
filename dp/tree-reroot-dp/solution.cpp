// Stepsort · Tree Rerooting DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-reroot-dp

vector<int> treeReroot(int n, vector<pair<int,int>>& edges) {
    vector<vector<int>> adj(n);
    for (auto [u,v] : edges) { adj[u].push_back(v); adj[v].push_back(u); }
    vector<int> subtree(n, 1), answer(n, 0);
    function<void(int,int)> dfs1 = [&](int u, int p) {
        for (int v : adj[u]) if (v != p) { dfs1(v, u); subtree[u] += subtree[v]; }
    };
    function<void(int,int)> dfs2 = [&](int u, int p) {
        if (p != -1) answer[u] = answer[p] + (n - subtree[u]) - subtree[u];
        for (int v : adj[u]) if (v != p) dfs2(v, u);
    };
    dfs1(0, -1);
    for (int i = 0; i < n; i++) answer[0] += subtree[i] - 1;
    dfs2(0, -1);
    return answer;
}
