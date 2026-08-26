// sortsort · Minimum Vertex Cut
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/min-vertex-cut

static int minVertexCut(int n, int[][] edges, int s, int t) {
    int N = 2 * n;
    int[][] cap = new int[N][N];
    for (int[] e : edges) cap[e[0]][e[1]] = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) cap[i][i + n] = 1;
    cap[s + n][s] = Integer.MAX_VALUE; cap[t][t + n] = Integer.MAX_VALUE;
    int flow = 0;
    while (true) {
        int[] parent = new int[N]; Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>(); q.add(s + n); parent[s + n] = s + n;
        while (!q.isEmpty() && parent[t + n] == -1) {
            int u = q.poll();
            for (int v = 0; v < N; v++)
                if (parent[v] == -1 && cap[u][v] > 0) { parent[v] = u; q.add(v); }
        }
        if (parent[t + n] == -1) break;
        int f = Integer.MAX_VALUE;
        for (int v = t + n; v != s + n; v = parent[v]) f = Math.min(f, cap[parent[v]][v]);
        for (int v = t + n; v != s + n; v = parent[v]) { cap[parent[v]][v] -= f; cap[v][parent[v]] += f; }
        flow += f;
    }
    return flow;
}
