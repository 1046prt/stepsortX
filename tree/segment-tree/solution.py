# sortsort · Segment Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/segment-tree

# Segment tree with range-sum query and point update

class SegmentTree:
    def __init__(self, values):
        self.n = len(values)
        self.tree = [0] * (4 * self.n)
        self._build(values, 1, 0, self.n - 1)

    def _build(self, values, node, lo, hi):
        if lo == hi:
            self.tree[node] = values[lo]
            return
        mid = (lo + hi) // 2
        self._build(values, 2 * node, lo, mid)
        self._build(values, 2 * node + 1, mid + 1, hi)
        self.tree[node] = self.tree[2 * node] + self.tree[2 * node + 1]

    def _query(self, node, lo, hi, l, r):
        # sum over the intersection of [l, r] with segment [lo, hi]
        if r < lo or hi < l:
            return 0
        if l <= lo and hi <= r:
            return self.tree[node]
        mid = (lo + hi) // 2
        left = self._query(2 * node, lo, mid, l, r)
        right = self._query(2 * node + 1, mid + 1, hi, l, r)
        return left + right

    def range_sum(self, l, r):
        # inclusive sum over indices l..r (0-based)
        return self._query(1, 0, self.n - 1, l, r)

    def _update(self, node, lo, hi, pos, value):
        if lo == hi:
            self.tree[node] = value
            return
        mid = (lo + hi) // 2
        if pos <= mid:
            self._update(2 * node, lo, mid, pos, value)
        else:
            self._update(2 * node + 1, mid + 1, hi, pos, value)
        self.tree[node] = self.tree[2 * node] + self.tree[2 * node + 1]

    def point_update(self, pos, value):
        # set arr[pos] = value and fix ancestors
        self._update(1, 0, self.n - 1, pos, value)


if __name__ == "__main__":
    arr = [2, 5, 1, 4, 9, 3]
    st = SegmentTree(arr)
    print("sum arr[1..3]:", st.range_sum(1, 3))
    print("sum arr[0..5]:", st.range_sum(0, 5))
    st.point_update(2, 10)  # arr[2] = 10
    print("after setting arr[2] = 10")
    print("sum arr[1..3]:", st.range_sum(1, 3))
    print("sum arr[0..5]:", st.range_sum(0, 5))
