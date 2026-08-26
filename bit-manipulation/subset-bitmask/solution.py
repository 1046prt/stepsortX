# Stepsort · Subsets via Bitmask
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-bitmask

def print_subsets(arr):
    n = len(arr)
    print("subsets of", arr)
    for mask in range(1 << n):
        chosen = [arr[i] for i in range(n) if mask & (1 << i)]
        print(mask, "->", chosen)


if __name__ == "__main__":
    print_subsets([1, 2, 3])
