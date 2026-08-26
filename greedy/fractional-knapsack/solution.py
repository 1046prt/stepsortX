# Stepsort · Fractional Knapsack
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fractional-knapsack

def fractional_knapsack(capacity, items):
    # items: list of (value, weight); sort by value/weight ratio descending
    ordered = sorted(items, key=lambda item: item[0] / item[1], reverse=True)
    total_value = 0.0
    remaining = capacity
    for value, weight in ordered:
        if remaining <= 0:
            break
        take = min(weight, remaining)
        total_value += value * take / weight
        remaining -= take
    return total_value


if __name__ == "__main__":
    items = [(60, 10), (100, 20), (120, 30)]
    best = fractional_knapsack(50, items)
    print("Maximum value:", best)
