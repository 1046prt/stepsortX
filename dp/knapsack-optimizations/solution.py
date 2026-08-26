# Stepsort · Knapsack Optimizations
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack-optimizations

def knapsack_bitset(weights, values, W):
    n = len(weights)
    bits = 1
    for w in weights: bits = max(bits, w)
    dp = [0] * (W + 1)
    for i in range(n):
        for w in range(W, weights[i] - 1, -1):
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    return dp[W]

def knapsack_mitm(items, W):
    n = len(items)
    mid = n // 2
    def gen(start, end):
        result = []
        for mask in range(1 << (end - start)):
            wt = val = 0
            for i in range(end - start):
                if mask & (1 << i):
                    wt += items[start + i][0]
                    val += items[start + i][1]
            if wt <= W: result.append((wt, val))
        return result
    left = gen(0, mid)
    right = gen(mid, n)
    right.sort()
    best = 0
    j = len(right) - 1
    for wt, val in left:
        while j >= 0 and right[j][0] + wt > W: j -= 1
        if j >= 0: best = max(best, val + right[j][1])
    return best

items = [(2,3),(3,4),(4,5),(5,6),(9,10)]
print("Bitset knapsack:", knapsack_bitset([i[0] for i in items], [i[1] for i in items], 20))
print("MITM knapsack:", knapsack_mitm(items, 20))
