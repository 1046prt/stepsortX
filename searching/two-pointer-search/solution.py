# Stepsort · Two Pointer Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-pointer-search

def two_pointer_pair_sum(arr, target):
    # Walk both ends of a sorted array inward based on the current sum.
    lo, hi = 0, len(arr) - 1
    while lo < hi:
        total = arr[lo] + arr[hi]
        if total == target:
            return (lo, hi)
        elif total < target:
            lo += 1
        else:
            hi -= 1
    return (-1, -1)


if __name__ == "__main__":
    data = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
    print("array:", data)
    print("pair for 24:", two_pointer_pair_sum(data, 24))
    print("pair for 200:", two_pointer_pair_sum(data, 200))
