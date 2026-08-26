// Stepsort · Suffix Array + LCP
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-array-lcp

static int[] buildSA(String s) {
    int n = s.length();
    Integer[] sa = new Integer[n];
    int[] rank = new int[n], tmp = new int[n];
    for (int i = 0; i < n; i++) { sa[i] = i; rank[i] = s.charAt(i); }
    for (int k = 1; k < n; k <<= 1) {
        final int kk = k;
        final int[] r = rank.clone();
        Arrays.sort(sa, (a, b) -> {
            if (r[a] != r[b]) return Integer.compare(r[a], r[b]);
            int ra = a + kk < n ? r[a + kk] : -1;
            int rb = b + kk < n ? r[b + kk] : -1;
            return Integer.compare(ra, rb);
        });
        tmp[sa[0]] = 0;
        for (int i = 1; i < n; i++) {
            int a = sa[i-1], b = sa[i];
            int ra1 = a + k < n ? r[a + k] : -1;
            int rb1 = b + k < n ? r[b + k] : -1;
            tmp[b] = tmp[a] + (r[a] < r[b] || (r[a] == r[b] && ra1 < rb1) ? 1 : 0);
        }
        rank = tmp.clone();
        if (rank[sa[n-1]] == n - 1) break;
    }
    int[] result = new int[n];
    for (int i = 0; i < n; i++) result[i] = sa[i];
    return result;
}
