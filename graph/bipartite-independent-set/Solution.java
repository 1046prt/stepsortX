// Stepsort · Bipartite Independent Set
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-independent-set

static int[] match;
static boolean bpm(int u, boolean[] seen, List<List<Integer>> adj) {
    for (int v : adj.get(u)) {
        if (!seen[v]) {
            seen[v] = true;
            if (match[v] == -1 || bpm(match[v], seen, adj)) { match[v] = u; return true; }
        }
    }
    return false;
}
static int[] bipartiteIndependentSet(int n, List<List<Integer>> adj, int[] leftNodes) {
    match = new int[n]; Arrays.fill(match, -1);
    int matching = 0;
    for (int u : leftNodes) { if (bpm(u, new boolean[n], adj)) matching++; }
    return new int[]{matching};
}
