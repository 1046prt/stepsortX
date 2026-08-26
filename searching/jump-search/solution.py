# sortsort · Jump Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/jump-search

import math


def jump_search(arr, target):
    # Jump ahead in blocks of size sqrt(n), then scan the block.
    n = len(arr)
    if n == 0:
        return -1
    step = max(1, math.isqrt(n))
    prev = 0
    curr = min(step, n)
    while arr[curr - 1] < target:
        prev = curr
        if curr == n:
            return -1
        curr = min(curr + step, n)
    for i in range(prev, curr):
        if arr[i] == target:
            return i
    return -1


if __name__ == "__main__":
    data = [1, 3, 5, 7, 9, 12, 15, 18, 21]
    print("array:", data)
    print("index of 12:", jump_search(data, 12))
    print("index of 10:", jump_search(data, 10))
