# sortsort · Coin Change
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-change

def coin_change(coins, amount):
    # Bottom-up DP over amounts; sentinel amount+1 means unreachable.
    inf = amount + 1
    dp = [inf] * (amount + 1)
    dp[0] = 0
    for coin in coins:
        for x in range(coin, amount + 1):
            if dp[x - coin] + 1 < dp[x]:
                dp[x] = dp[x - coin] + 1
    return -1 if dp[amount] >= inf else dp[amount]


if __name__ == "__main__":
    print(coin_change([1, 2, 5], 11))  # 3
    print(coin_change([2], 3))         # -1
