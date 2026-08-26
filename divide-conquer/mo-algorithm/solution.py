# Stepsort · Mo's Algorithm
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/mo-algorithm

from math import isqrt


def mo_queries(arr, queries):
    n = len(arr)
    block = max(1, isqrt(n))
    order = sorted(
        range(len(queries)),
        key=lambda i: (queries[i][0] // block, queries[i][1]),
    )

    freq = {}
    distinct = 0
    cur_l, cur_r = 0, -1
    results = [0] * len(queries)

    def add(i):
        nonlocal distinct
        freq[arr[i]] = freq.get(arr[i], 0) + 1
        if freq[arr[i]] == 1:
            distinct += 1

    def remove(i):
        nonlocal distinct
        freq[arr[i]] -= 1
        if freq[arr[i]] == 0:
            distinct -= 1

    for qi in order:
        l, r = queries[qi]
        while cur_r < r:
            cur_r += 1
            add(cur_r)
        while cur_l > l:
            cur_l -= 1
            add(cur_l)
        while cur_r > r:
            remove(cur_r)
            cur_r -= 1
        while cur_l < l:
            remove(cur_l)
            cur_l += 1
        results[qi] = distinct
    return results


if __name__ == "__main__":
    arr = [1, 1, 2, 3, 3, 4, 1, 2, 2, 1]
    queries = [(0, 4), (1, 6), (3, 9), (2, 7)]
    print(mo_queries(arr, queries))   # [3, 3, 3, 2]
