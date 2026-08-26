// Stepsort · Bipartite Independent Set
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-independent-set

pair<int,vector<int>> bipartiteIndependentSet(int n, vector<vector<int>>& adj, vector<int>& leftNodes) {
    vector<int> match(n, -1);
    auto bpm = [&](auto&& self, int u, vector<bool>& seen) -> bool {
        for (int v : adj[u]) {
            if (!seen[v]) {
                seen[v] = true;
                if (match[v] == -1 || self(self, match[v], seen)) { match[v] = u; return true; }
            }
        }
        return false;
    };
    int matching = 0;
    for (int u : leftNodes) { vector<bool> seen(n, false); if (bpm(bpm, u, seen)) matching++; }
    vector<bool> isLeft(n, false), matchedR(n, false);
    for (int u : leftNodes) isLeft[u] = true;
    for (int v = 0; v < n; v++) if (match[v] != -1) matchedR[v] = true;
    vector<int> independent;
    for (int u : leftNodes) if (match[u] == -1) independent.push_back(u);
    for (int v = 0; v < n; v++) if (!matchedR[v]) independent.push_back(v);
    return {matching, independent};
}
