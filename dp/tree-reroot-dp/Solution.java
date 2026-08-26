// sortsort · Tree Rerooting DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-reroot-dp

static int[] treeReroot(int n, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
    int[] subtree = new int[n], answer = new int[n];
    Arrays.fill(subtree, 1);
    dfs1(0, -1, adj, subtree);
    for (int i = 0; i < n; i++) answer[0] += subtree[i] - 1;
    dfs2(0, -1, adj, subtree, answer);
    return answer;
}
static void dfs1(int u, int p, List<List<Integer>> adj, int[] subtree) {
    for (int v : adj.get(u)) if (v != p) { dfs1(v, u, adj, subtree); subtree[u] += subtree[v]; }
}
static void dfs2(int u, int p, List<List<Integer>> adj, int[] subtree, int[] answer) {
    if (p != -1) answer[u] = answer[p] + (subtree.length - subtree[u]) - subtree[u];
    for (int v : adj.get(u)) if (v != p) dfs2(v, u, adj, subtree, answer);
}
