# sortsort · Sparse Table (RMQ)
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sparse-table

from math import log2


def build_sparse(arr):
    n = len(arr)
    LOG = int(log2(n)) + 1
    sp = [[0] * n for _ in range(LOG)]
    sp[0] = arr[:]
    for j in range(1, LOG):
        length = 1 << j
        for i in range(n - length + 1):
            half = 1 << (j - 1)
            sp[j][i] = min(sp[j - 1][i], sp[j - 1][i + half])
    return sp, LOG


def query(sp, l, r):
    k = int(log2(r - l + 1))
    return min(sp[k][l], sp[k][r - (1 << k) + 1])


if __name__ == "__main__":
    arr = [4, 2, 8, 1, 6, 3, 7, 5]
    sp, LOG = build_sparse(arr)
    print(query(sp, 2, 6))   # 1
    print(query(sp, 0, 3))   # 2
