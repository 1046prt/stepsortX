# Stepsort · Exponential Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/exponential-search

def binary_search_range(arr, target, lo, hi):
    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1


def exponential_search(arr, target):
    # Grow the bound exponentially, then binary search the last block.
    n = len(arr)
    if n == 0:
        return -1
    if arr[0] == target:
        return 0
    bound = 1
    while bound < n and arr[bound] <= target:
        bound *= 2
    return binary_search_range(arr, target, bound // 2, min(bound, n - 1))


if __name__ == "__main__":
    data = [2, 4, 8, 16, 32, 64, 128, 256]
    print("array:", data)
    print("index of 64:", exponential_search(data, 64))
    print("index of 100:", exponential_search(data, 100))
