# sortsort · Merge Sort Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-sort-tree

from bisect import bisect_right

arr = [5, 2, 6, 1, 3, 4, 7]
n = len(arr)
tree = [[] for _ in range(4 * n)]


def build(node, l, r):
    if l == r:
        tree[node] = [arr[l]]
        return
    mid = (l + r) // 2
    build(2 * node, l, mid)
    build(2 * node + 1, mid + 1, r)
    a, b = tree[2 * node], tree[2 * node + 1]
    merged = []
    i = j = 0
    while i < len(a) and j < len(b):
        if a[i] <= b[j]:
            merged.append(a[i])
            i += 1
        else:
            merged.append(b[j])
            j += 1
    merged.extend(a[i:])
    merged.extend(b[j:])
    tree[node] = merged


def query(node, l, r, lo, hi, x):
    if hi < l or r < lo:
        return 0
    if lo <= l and r <= hi:
        return bisect_right(tree[node], x)
    mid = (l + r) // 2
    return query(2 * node, l, mid, lo, hi, x) + query(2 * node + 1, mid + 1, r, lo, hi, x)


if __name__ == "__main__":
    build(1, 0, n - 1)
    count = query(1, 0, n - 1, 1, 5, 4)
    print("count of values <= 4 in arr[1..5]:", count)   # expect 4
