// sortsort · Subset Convolution
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-convolution

vector<int> subsetConvolution(vector<int>& a, vector<int>& b) {
    int n = a.size(), bits = __builtin_ctz(n);
    vector<vector<int>> fa(bits+1, vector<int>(n)), fb(bits+1, vector<int>(n));
    for (int mask = 0; mask < n; mask++) {
        int pc = __builtin_popcount(mask);
        fa[pc][mask] = a[mask]; fb[pc][mask] = b[mask];
    }
    for (int k = 0; k <= bits; k++)
        for (int i = 0; i < bits; i++)
            for (int mask = 0; mask < n; mask++)
                if (mask & (1 << i)) { fa[k][mask] += fa[k][mask^(1<<i)]; fb[k][mask] += fb[k][mask^(1<<i)]; }
    vector<vector<int>> fc(bits+1, vector<int>(n));
    for (int mask = 0; mask < n; mask++)
        for (int i = 0; i <= bits; i++)
            for (int j = 0; j <= bits - i; j++)
                fc[i+j][mask] += fa[i][mask] * fb[j][mask];
    for (int k = 0; k <= bits; k++)
        for (int i = 0; i < bits; i++)
            for (int mask = 0; mask < n; mask++)
                if (mask & (1 << i)) fc[k][mask] -= fc[k][mask^(1<<i)];
    return fc[bits];
}
