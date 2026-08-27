# Stepsort · Best Time to Buy/Sell Stock
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/best-time-to-buy-sell-stock

def max_profit(prices):
    min_price = float('inf')
    max_profit = 0
    for price in prices:
        min_price = min(min_price, price)
        max_profit = max(max_profit, price - min_price)
    return max_profit

if __name__ == "__main__":
    print(max_profit([7, 1, 5, 3, 6, 4]))  # 5
    print(max_profit([7, 6, 4, 3, 1]))     # 0
