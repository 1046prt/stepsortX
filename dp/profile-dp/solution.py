# Stepsort · Profile Dynamic Programming
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/profile-dp

def count_tilings(n):
    if n == 0: return 1
    full = (1 << 3) - 1
    dp = [0] * (1 << 3)
    dp[full] = 1

    def dfs(col, mask):
        if col == n: return 1 if mask == full else 0
        if mask == full:
            if (col, full) in memo: return memo[(col, full)]
        key = (col, mask)
        if key in memo: return memo[key]
        result = 0
        if mask & 1:
            if col + 1 < n:
                result += dfs(col + 1, (mask >> 1) | (1 << 2))
            result += dfs(col + 1, (mask >> 1) & ~1)
        else:
            if mask & 2:
                result += dfs(col + 1, (mask >> 1) | (1 << 2) | 1)
            if mask & 4:
                result += dfs(col + 1, (mask >> 1) | 1)
            result += dfs(col + 1, (mask >> 1) | 2)
        memo[key] = result
        return result

    memo = {}
    return dfs(0, full)

print(count_tilings(4))
