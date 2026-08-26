# sortsort · Quick Select
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/quick-select

def median_of_three(arr, lo, hi):
    # deterministic pivot: value-sorted middle among first, middle, last
    mid = (lo + hi) // 2
    trio = sorted([(arr[lo], lo), (arr[mid], mid), (arr[hi], hi)])
    return trio[1][1]


def partition(arr, lo, hi):
    pivot_index = median_of_three(arr, lo, hi)
    arr[pivot_index], arr[hi] = arr[hi], arr[pivot_index]
    pivot = arr[hi]
    store = lo
    for i in range(lo, hi):
        if arr[i] < pivot:
            arr[i], arr[store] = arr[store], arr[i]
            store += 1
    arr[store], arr[hi] = arr[hi], arr[store]
    return store


def quick_select(nums, k):
    # k-th smallest (zero-indexed); runs on a copy so nums stays intact
    arr = list(nums)
    lo, hi = 0, len(arr) - 1
    while True:
        if lo == hi:
            return arr[lo]
        p = partition(arr, lo, hi)
        if k == p:
            return arr[p]
        if k < p:
            hi = p - 1
        else:
            lo = p + 1


if __name__ == "__main__":
    data = [7, 2, 9, 4, 1, 8, 6, 3, 5]
    print("data:", data)
    for k in (0, 3, 8):
        print("rank", k + 1, "smallest:", quick_select(data, k))
