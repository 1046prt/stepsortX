# sortsort · Bitmask DP (TSP)
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitmask-dp-tsp

dist = [
    [0, 10, 15, 20],
    [10, 0, 35, 25],
    [15, 35, 0, 30],
    [20, 25, 30, 0],
]
n = 4
FULL = (1 << n) - 1
dp = {}

dp[(1, 0)] = 0
    for mask in range(1, FULL + 1, 2):
        for last in range(n):
            if not (mask & (1 << last)) or (mask, last) not in dp:
                continue
        for nxt in range(n):
            if mask & (1 << nxt):
                continue
            nm = mask | (1 << nxt)
            key = (nm, nxt)
            cand = dp[(mask, last)] + dist[last][nxt]
            if key not in dp or cand < dp[key]:
                dp[key] = cand

best = min(dp[(FULL, last)] + dist[last][0] for last in range(1, n))
print("optimal tour cost:", best)   # 80
