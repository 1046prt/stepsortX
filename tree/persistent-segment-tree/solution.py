# Stepsort · Persistent Segment Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/persistent-segment-tree

class Node:
    __slots__ = ["sum", "left", "right"]

    def __init__(self, s=0):
        self.sum = s
        self.left = None
        self.right = None


def build(arr, l, r):
    if l == r:
        return Node(arr[l])
    mid = (l + r) // 2
    node = Node()
    node.left = build(arr, l, mid)
    node.right = build(arr, mid + 1, r)
    node.sum = node.left.sum + node.right.sum
    return node


def update(prev, l, r, idx, val):
    node = Node(prev.sum)
    if l == r:
        node.sum = val
        return node
    mid = (l + r) // 2
    if idx <= mid:
        node.left = update(prev.left, l, mid, idx, val)
        node.right = prev.right
    else:
        node.right = update(prev.right, mid + 1, r, idx, val)
        node.left = prev.left
    return node


def query(node, l, r, lo, hi):
    if hi < l or r < lo:
        return 0
    if lo <= l and r <= hi:
        return node.sum
    mid = (l + r) // 2
    return query(node.left, l, mid, lo, hi) + query(node.right, mid + 1, r, lo, hi)


if __name__ == "__main__":
    arr = [1, 3, 5, 7]
    roots = [build(arr, 0, 3)]
    roots.append(update(roots[0], 0, 3, 2, 9))
    print("v0 sum[0..1] =", query(roots[0], 0, 3, 0, 1))   # expect 4
    print("v1 sum[0..1] =", query(roots[1], 0, 3, 0, 1))   # expect 4
