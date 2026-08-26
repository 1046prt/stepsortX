# sortsort · Ternary Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ternary-search

def ternary_search(arr, target):
    # Split the sorted range into three parts using two midpoints.
    lo, hi = 0, len(arr) - 1
    while lo <= hi:
        third = (hi - lo) // 3
        m1 = lo + third
        m2 = hi - third
        if arr[m1] == target:
            return m1
        if arr[m2] == target:
            return m2
        if target < arr[m1]:
            hi = m1 - 1
        elif target > arr[m2]:
            lo = m2 + 1
        else:
            lo = m1 + 1
            hi = m2 - 1
    return -1


if __name__ == "__main__":
    data = [1, 4, 7, 12, 15, 19, 24, 31, 40]
    print("array:", data)
    print("index of 19:", ternary_search(data, 19))
    print("index of 20:", ternary_search(data, 20))
