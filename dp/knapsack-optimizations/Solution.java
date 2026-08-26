// Stepsort · Knapsack Optimizations
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack-optimizations

static int knapsackBitset(int[] wt, int[] val, int W) {
    int[] dp = new int[W + 1];
    for (int i = 0; i < wt.length; i++)
        for (int w = W; w >= wt[i]; w--)
            dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
    return dp[W];
}

static int knapsackMITM(int[][] items, int W) {
    int n = items.length, mid = n / 2;
    List<int[]> left = new ArrayList<>(), right = new ArrayList<>();
    for (int mask = 0; mask < (1 << mid); mask++) {
        int w = 0, v = 0;
        for (int i = 0; i < mid; i++) if ((mask & (1<<i)) != 0) { w += items[i][0]; v += items[i][1]; }
        if (w <= W) left.add(new int[]{w, v});
    }
    for (int mask = 0; mask < (1 << (n-mid)); mask++) {
        int w = 0, v = 0;
        for (int i = 0; i < n-mid; i++) if ((mask & (1<<i)) != 0) { w += items[mid+i][0]; v += items[mid+i][1]; }
        if (w <= W) right.add(new int[]{w, v});
    }
    right.sort(Comparator.comparingInt(a -> a[0]));
    int best = 0, j = right.size() - 1;
    for (int[] l : left) {
        while (j >= 0 && right.get(j)[0] + l[0] > W) j--;
        if (j >= 0) best = Math.max(best, l[1] + right.get(j)[1]);
    }
    return best;
}
