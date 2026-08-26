// sortsort · Multi-Source 0-1 BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-01-bfs

vector<int> multiSource01BFS(vector<vector<pair<int,int>>>& graph, vector<int>& sources) {
    int n = graph.size();
    vector<int> dist(n, INT_MAX);
    deque<int> dq;
    for (int s : sources) { dist[s] = 0; dq.push_front(s); }
    while (!dq.empty()) {
        int u = dq.front(); dq.pop_front();
        for (auto [v, w] : graph[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                if (w == 0) dq.push_front(v);
                else dq.push_back(v);
            }
        }
    }
    return dist;
}
