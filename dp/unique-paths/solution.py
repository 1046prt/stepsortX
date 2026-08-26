# Stepsort · Unique Paths
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/unique-paths

def unique_paths(m: int, n: int) -> int:
    # dp[j] = number of ways to reach column j in the current row
    dp = [1] * n
    for _ in range(1, m):
        for j in range(1, n):
            dp[j] += dp[j - 1]
    return dp[n - 1]


if __name__ == "__main__":
    print("Unique paths in a 3 x 7 grid:", unique_paths(3, 7))
