// Stepsort · Profile Dynamic Programming
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/profile-dp

int countTilings(int n) {
    int full = (1 << 3) - 1;
    unordered_map<long long,int> memo;
    auto dfs = [&](auto&& self, int col, int mask) -> int {
        if (col == n) return mask == full ? 1 : 0;
        long long key = ((long long)col << 3) | mask;
        if (memo.count(key)) return memo[key];
        int result = 0;
        if (mask & 1) {
            if (col + 1 < n) result += self(self, col+1, (mask>>1)|(1<<2));
            result += self(self, col+1, (mask>>1)&~1);
        } else {
            if (mask & 2) result += self(self, col+1, (mask>>1)|(1<<2)|1);
            if (mask & 4) result += self(self, col+1, (mask>>1)|1);
            result += self(self, col+1, (mask>>1)|2);
        }
        return memo[key] = result;
    };
    return dfs(dfs, 0, full);
}
