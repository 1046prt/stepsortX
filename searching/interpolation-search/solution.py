# Stepsort · Interpolation Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interpolation-search

def interpolation_search(arr, target):
    # Probe position estimated from value distribution (sorted data).
    lo, hi = 0, len(arr) - 1
    while lo <= hi and arr[lo] <= target <= arr[hi]:
        if arr[lo] == arr[hi]:
            return lo if arr[lo] == target else -1
        pos = lo + (target - arr[lo]) * (hi - lo) // (arr[hi] - arr[lo])
        if arr[pos] == target:
            return pos
        elif arr[pos] < target:
            lo = pos + 1
        else:
            hi = pos - 1
    return -1


if __name__ == "__main__":
    data = [10, 20, 30, 40, 50, 60, 70, 80]
    print("array:", data)
    print("index of 50:", interpolation_search(data, 50))
    print("index of 45:", interpolation_search(data, 45))
