# sortsort · Divide & Conquer Optimization
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dc-optimization

def min_cost_partition(arr, g):
    n = len(arr)
    prefix = [0]
    for v in arr:
        prefix.append(prefix[-1] + v)

    def seg_cost(l, r):   # inclusive indices
        s = prefix[r + 1] - prefix[l]
        return s * s

    INF = float("inf")
    prev = [INF] * (n + 1)
    prev[0] = 0

    for layer in range(1, g + 1):
        cur = [INF] * (n + 1)

        def rec(lo, hi, klo, khi):
            if lo > hi:
                return
            mid = (lo + hi) // 2
            best, arg = INF, max(klo, layer - 1)
            for k in range(max(klo, layer - 1), min(khi, mid - 1) + 1):
                cand = prev[k] + seg_cost(k, mid)
                if cand < best:
                    best, arg = cand, k
            cur[mid] = best
            rec(lo, mid - 1, klo, arg)
            rec(mid + 1, hi, arg, khi)

        rec(layer, n - 1, layer - 1, n - 1)
        prev = cur

    return prev[n - 1]


if __name__ == "__main__":
    print(min_cost_partition([7, 2, 3, 9, 4, 1], 3))
