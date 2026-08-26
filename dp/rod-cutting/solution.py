# Stepsort · Rod Cutting
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rod-cutting

def rod_cutting(prices: list[int], n: int) -> tuple[int, list[int]]:
    # prices[k] is the revenue of a piece of length k+1.
    # dp[L] = best revenue for a rod of length L.
    dp = [0] * (n + 1)
    cuts = [[] for _ in range(n + 1)]  # piece sizes achieving dp[L]
    for length in range(1, n + 1):
        for first in range(1, length + 1):  # try every size for the first piece
            candidate = prices[first - 1] + dp[length - first]
            if candidate > dp[length]:
                dp[length] = candidate
                cuts[length] = cuts[length - first] + [first]
    return dp[n], cuts[n]


if __name__ == "__main__":
    prices = [1, 5, 8, 9, 10, 17, 17, 20]
    revenue, pieces = rod_cutting(prices, 8)
    print("Best revenue:", revenue)
    print("Piece lengths:", pieces)
