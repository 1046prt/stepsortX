# sortsort · Sentinel Linear Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sentinel-search

def sentinel_search(arr, target):
    # Park the target at the end so the scan needs no bounds check.
    n = len(arr)
    if n == 0:
        return -1
    last = arr[-1]
    arr[-1] = target
    i = 0
    while arr[i] != target:
        i += 1
    arr[-1] = last
    if i < n - 1 or arr[n - 1] == target:
        return i
    return -1


if __name__ == "__main__":
    data = [4, 2, 7, 1, 9, 5]
    print("array:", data)
    print("index of 9:", sentinel_search(data, 9))
    print("index of 5:", sentinel_search(data, 5))
    print("index of 3:", sentinel_search(data, 3))
    print("array after search:", data)
