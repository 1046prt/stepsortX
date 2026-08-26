# sortsort · Interval DP (Matrix Chain)
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-dp-matrix-chain

dims = [40, 20, 30, 10, 30]
n = len(dims) - 1
INF = float("inf")
dp = [[INF] * n for _ in range(n)]
for i in range(n):
    dp[i][i] = 0

for length in range(2, n + 1):
    for i in range(n - length + 1):
        j = i + length - 1
        for k in range(i, j):
            cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1]
            dp[i][j] = min(dp[i][j], cost)

print("minimum multiplications:", dp[0][n - 1])   # 26000
