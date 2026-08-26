// Stepsort · General Graph Matching
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/general-matching

static int blossomMatch(int n, List<List<Integer>> adj) {
    int[] match = new int[n], label = new int[n], parent = new int[n], base = new int[n];
    Arrays.fill(match, -1);
    for (int i = 0; i < n; i++) base[i] = i;
    int result = 0;
    for (int root = 0; root < n; root++) {
        if (match[root] != -1) continue;
        Arrays.fill(label, 0);
        for (int i = 0; i < n; i++) base[i] = i;
        Queue<Integer> q = new LinkedList<>();
        q.add(root); label[root] = 1;
        boolean found = false;
        while (!q.isEmpty() && !found) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (label[v] == 0) {
                    label[v] = 2; parent[v] = u;
                    if (match[v] == -1) {
                        for (int x = v; x != -1; ) {
                            int px = parent[x], pmx = match[px];
                            match[x] = px; match[px] = x; x = pmx;
                        }
                        found = true; break;
                    }
                    label[match[v]] = 1; q.add(match[v]);
                }
            }
        }
        if (found) result++;
    }
    return result;
}
