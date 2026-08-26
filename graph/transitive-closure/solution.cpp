// Stepsort · Transitive Closure
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/transitive-closure

vector<vector<bool>> transitiveClosure(int n, vector<pair<int,int>>& edges) {
    vector<vector<bool>> tc(n, vector<bool>(n, false));
    for (auto [u,v] : edges) tc[u][v] = true;
    for (int i = 0; i < n; i++) tc[i][i] = true;
    for (int k = 0; k < n; k++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tc[i][k] && tc[k][j]) tc[i][j] = true;
    return tc;
}
