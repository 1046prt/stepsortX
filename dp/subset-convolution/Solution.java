// sortsort · Subset Convolution
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-convolution

static int[] subsetConvolution(int[] a, int[] b) {
    int n = a.length, bits = 31 - Integer.numberOfLeadingZeros(n);
    int[][] fa = new int[bits+1][n], fb = new int[bits+1][n];
    for (int mask = 0; mask < n; mask++) {
        int pc = Integer.bitCount(mask);
        fa[pc][mask] = a[mask]; fb[pc][mask] = b[mask];
    }
    for (int k = 0; k <= bits; k++)
        for (int i = 0; i < bits; i++)
            for (int mask = 0; mask < n; mask++)
                if ((mask & (1 << i)) != 0) { fa[k][mask] += fa[k][mask^(1<<i)]; fb[k][mask] += fb[k][mask^(1<<i)]; }
    int[][] fc = new int[bits+1][n];
    for (int mask = 0; mask < n; mask++)
        for (int i = 0; i <= bits; i++)
            for (int j = 0; j <= bits - i; j++)
                fc[i+j][mask] += fa[i][mask] * fb[j][mask];
    for (int k = 0; k <= bits; k++)
        for (int i = 0; i < bits; i++)
            for (int mask = 0; mask < n; mask++)
                if ((mask & (1 << i)) != 0) fc[k][mask] -= fc[k][mask^(1<<i)];
    return fc[bits];
}
