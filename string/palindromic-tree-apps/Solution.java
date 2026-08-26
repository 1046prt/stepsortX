// Stepsort · Palindromic Tree Applications
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindromic-tree-apps

static int distinctPalindromes(String s) {
    int n = s.length();
    int[][] tree = new int[n + 3][26];
    int[] len = new int[n + 3], suffix = new int[n + 3], count = new int[n + 3];
    len[0] = 0; len[1] = -1; suffix[0] = 1; suffix[1] = 0;
    int last = 0, size = 2;
    for (int pos = 0; pos < n; pos++) {
        int cur = last;
        char c = s.charAt(pos);
        while (true) {
            int curlen = len[cur];
            if (pos - curlen - 1 >= 0 && s.charAt(pos - curlen - 1) == c) break;
            cur = suffix[cur];
        }
        int ch = c - 'a';
        if (tree[cur][ch] == 0) {
            tree[cur][ch] = size;
            len[size] = len[cur] + 2;
            int q = suffix[cur];
            while (true) {
                if (pos - len[q] - 1 >= 0 && s.charAt(pos - len[q] - 1) == c) break;
                q = suffix[q];
            }
            suffix[size] = tree[q][ch] > 0 ? tree[q][ch] : 1;
            size++;
        }
        count[tree[cur][ch]]++;
        last = tree[cur][ch];
    }
    return size - 2;
}
