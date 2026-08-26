// Stepsort · Suffix Automaton Applications
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton-apps

static long distinctSubstrings(String s) {
    int[][] next = new int[2 * s.length() + 3][26];
    int[] len = new int[2 * s.length() + 3];
    int[] link = new int[2 * s.length() + 3];
    int last = 0, size = 1;
    link[0] = -1; len[0] = 0;
    for (char ch : s.toCharArray()) {
        int c = ch - 'a', p = last, cur = size++;
        len[cur] = len[p] + 1;
        while (p >= 0 && next[p][c] == 0) { next[p][c] = cur; p = link[p]; }
        if (p == -1) link[cur] = 0;
        else {
            int q = next[p][c];
            if (len[p] + 1 == len[q]) link[cur] = q;
            else {
                int clone = size++;
                len[clone] = len[p] + 1;
                link[clone] = link[q];
                System.arraycopy(next[q], 0, next[clone], 0, 26);
                while (p >= 0 && next[p][c] == q) { next[p][c] = clone; p = link[p]; }
                link[q] = link[cur] = clone;
            }
        }
        last = cur;
    }
    long total = 0;
    for (int i = 1; i < size; i++) total += len[i] - len[link[i]];
    return total;
}
