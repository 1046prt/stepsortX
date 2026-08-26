// sortsort · Minimum Cut Reconstruction
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-cut-reconstruction

static int[][] cap;
static int bfs(int s, int t, int[] parent, int n) {
    Arrays.fill(parent, -1);
    Queue<Integer> q = new LinkedList<>(); q.add(s); parent[s] = s;
    while (!q.isEmpty() && parent[t] == -1) {
        int u = q.poll();
        for (int v = 0; v < n; v++)
            if (parent[v] == -1 && cap[u][v] > 0) { parent[v] = u; q.add(v); }
    }
    if (parent[t] == -1) return 0;
    int f = Integer.MAX_VALUE;
    for (int v = t; v != s; v = parent[v]) f = Math.min(f, cap[parent[v]][v]);
    for (int v = t; v != s; v = parent[v]) { cap[parent[v]][v] -= f; cap[v][parent[v]] += f; }
    return f;
}
