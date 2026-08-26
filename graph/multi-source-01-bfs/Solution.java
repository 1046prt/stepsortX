// sortsort · Multi-Source 0-1 BFS
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-01-bfs

static int[] multiSource01BFS(List<List<int[]>> graph, int[] sources) {
    int n = graph.size();
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    ArrayDeque<Integer> dq = new ArrayDeque<>();
    for (int s : sources) { dist[s] = 0; dq.addFirst(s); }
    while (!dq.isEmpty()) {
        int u = dq.pollFirst();
        for (int[] edge : graph.get(u)) {
            int v = edge[0], w = edge[1];
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                if (w == 0) dq.addFirst(v); else dq.addLast(v);
            }
        }
    }
    return dist;
}
