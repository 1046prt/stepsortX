// sortsort · Transitive Closure
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/transitive-closure

static boolean[][] transitiveClosure(int n, int[][] edges) {
    boolean[][] tc = new boolean[n][n];
    for (int[] e : edges) tc[e[0]][e[1]] = true;
    for (int i = 0; i < n; i++) tc[i][i] = true;
    for (int k = 0; k < n; k++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tc[i][k] && tc[k][j]) tc[i][j] = true;
    return tc;
}
