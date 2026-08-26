// sortsort · DAG Dynamic Programming
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-dp

static int[] dagLongestPath(int n, int[][] edges) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    int[] indeg = new int[n];
    for (int[] e : edges) { adj.get(e[0]).add(new int[]{e[1], e[2]}); indeg[e[1]]++; }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) if (indeg[i] == 0) q.add(i);
    List<Integer> topo = new ArrayList<>();
    while (!q.isEmpty()) { int u = q.poll(); topo.add(u);
        for (int[] e : adj.get(u)) if (--indeg[e[0]] == 0) q.add(e[0]); }
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MIN_VALUE);
    for (int i = 0; i < n; i++) if (indeg[i] == 0) dist[i] = 0;
    for (int u : topo)
        for (int[] e : adj.get(u))
            dist[e[0]] = Math.max(dist[e[0]], dist[u] + e[1]);
    return dist;
}
