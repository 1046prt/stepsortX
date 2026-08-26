// Stepsort · Profile Dynamic Programming
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/profile-dp

static int countTilings(int n) {
    int full = (1 << 3) - 1;
    Map<Long,Integer> memo = new HashMap<>();
    return dfs(0, full, n, memo);
}
static int dfs(int col, int mask, int n, Map<Long,Integer> memo) {
    if (col == n) return mask == full ? 1 : 0;
    long key = ((long)col << 3) | mask;
    if (memo.containsKey(key)) return memo.get(key);
    int result = 0;
    if ((mask & 1) != 0) {
        if (col + 1 < n) result += dfs(col+1, (mask>>1)|(1<<2), n, memo);
        result += dfs(col+1, (mask>>1)&~1, n, memo);
    } else {
        if ((mask & 2) != 0) result += dfs(col+1, (mask>>1)|(1<<2)|1, n, memo);
        if ((mask & 4) != 0) result += dfs(col+1, (mask>>1)|1, n, memo);
        result += dfs(col+1, (mask>>1)|2, n, memo);
    }
    memo.put(key, result);
    return result;
}
