// Stepsort · Multi-Constraint Digit DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-constraint-digit-dp

static long countNumbers(long L, long R) {
    return solve(R) - solve(L - 1);
}
static long solve(long n) {
    if (n < 0) return 0;
    char[] s = Long.toString(n).toCharArray();
    Map<Long,Long> memo = new HashMap<>();
    return dp(0, 1, 0, -1, false, s, memo);
}
static long dp(int pos, int tight, int sumPar, int last, boolean started, char[] s, Map<Long,Long> memo) {
    if (pos == s.length) return started && sumPar == 0 ? 1 : 0;
    long key = ((long)pos << 32) | ((long)tight << 30) | (sumPar << 28) | ((last+1) << 20) | (started ? 1 : 0);
    if (memo.containsKey(key)) return memo.get(key);
    int limit = tight ? s[pos] - '0' : 9;
    long result = 0;
    for (int d = 0; d <= limit; d++) {
        int ntight = tight && (d == limit) ? 1 : 0;
        boolean nstarted = started || d > 0;
        int np = sumPar, nl = last;
        if (nstarted) {
            np = (sumPar + d) % 2;
            if (last != -1 && d == last) continue;
            nl = d;
        }
        result += dp(pos+1, ntight, np, nl, nstarted, s, memo);
    }
    memo.put(key, result);
    return result;
}
