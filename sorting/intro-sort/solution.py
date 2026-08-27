# Stepsort · IntroSort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/intro-sort

import heapq

def intro_sort(arr):
    max_depth = 2 * (len(arr).bit_length())
    _intro_sort(arr, 0, len(arr), max_depth)
    return arr

def _intro_sort(arr, lo, hi, depth_limit):
    while hi - lo > 16:
        if depth_limit == 0:
            heapq.heapify(arr[lo:hi])
            for i in range(hi - 1, lo - 1, -1):
                arr[lo], arr[i] = arr[i], arr[lo]
                heapq._siftdown(arr[lo:i+1], 0, i - lo)
            return
        depth_limit -= 1
        p = _partition(arr, lo, hi)
        _intro_sort(arr, p + 1, hi, depth_limit)
        hi = p
    for i in range(lo + 1, hi):
        key = arr[i]
        j = i - 1
        while j >= lo and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key

def _partition(arr, lo, hi):
    mid = (lo + hi) // 2
    if arr[lo] > arr[mid]:
        arr[lo], arr[mid] = arr[mid], arr[lo]
    if arr[lo] > arr[hi - 1]:
        arr[lo], arr[hi - 1] = arr[hi - 1], arr[lo]
    if arr[mid] > arr[hi - 1]:
        arr[mid], arr[hi - 1] = arr[hi - 1], arr[mid]
    arr[mid], arr[hi - 2] = arr[hi - 2], arr[mid]
    pivot = arr[hi - 2]
    i = lo
    j = hi - 2
    while True:
        i += 1
        while arr[i] < pivot: i += 1
        j -= 1
        while arr[j] > pivot: j -= 1
        if i >= j: break
        arr[i], arr[j] = arr[j], arr[i]
    arr[i], arr[hi - 2] = arr[hi - 2], arr[i]
    return i

if __name__ == "__main__":
    data = [5, 1, 4, 2, 8]
    print("sorted:", intro_sort(data))
