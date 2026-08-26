# sortsort · 0/1 Knapsack
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knapsack

def knapsack(weights, values, capacity):
    # dp[i][w] = best value using the first i items with capacity w
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            dp[i][w] = dp[i - 1][w]  # skip item i-1
            if weights[i - 1] <= w:
                take = dp[i - 1][w - weights[i - 1]] + values[i - 1]
                if take > dp[i][w]:
                    dp[i][w] = take
    # Walk back through the table to recover the chosen items
    chosen = []
    w = capacity
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i - 1][w]:  # item i-1 must have been taken
            chosen.append(i - 1)
            w -= weights[i - 1]
    chosen.reverse()
    return dp[n][capacity], chosen


if __name__ == "__main__":
    weights = [1, 3, 4, 5]
    values = [1, 4, 5, 7]
    capacity = 7
    best, items = knapsack(weights, values, capacity)
    print("Best achievable value:", best)
    for i in items:
        print(f"  item {i}: weight={weights[i]}, value={values[i]}")
