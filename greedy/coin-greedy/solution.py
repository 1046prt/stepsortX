# sortsort · Coin Change (Greedy)
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/coin-greedy

def coin_change_greedy(amount, denominations):
    # repeatedly take the largest coin that fits
    coins = sorted(denominations, reverse=True)
    change = []
    for coin in coins:
        while amount >= coin:
            amount -= coin
            change.append(coin)
    return change


if __name__ == "__main__":
    print("Change for 93:", coin_change_greedy(93, [25, 10, 5, 1]))
    print("Coins used:", len(coin_change_greedy(93, [25, 10, 5, 1])))
    print("Change for 2890:", coin_change_greedy(2890, [1000, 500, 100, 50, 20, 10, 5, 1]))
