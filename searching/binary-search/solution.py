# Stepsort · Binary Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search

def binary_search(arr, target):
    # Iterative search on a sorted array.
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1


if __name__ == "__main__":
    data = [1, 3, 5, 7, 9, 11, 13]
    print("array:", data)
    print("index of 7:", binary_search(data, 7))
    print("index of 4:", binary_search(data, 4))
