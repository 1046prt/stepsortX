# Stepsort · Lazy Propagation Segment Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lazy-segment-tree

class LazySegTree:
    def __init__(self, arr):
        self.n = len(arr)
        self.sum = [0] * (4 * self.n)
        self.lazy = [0] * (4 * self.n)
        self._build(1, 0, self.n - 1, arr)

    def _build(self, node, l, r, arr):
        if l == r:
            self.sum[node] = arr[l]
            return
        m = (l + r) // 2
        self._build(2*node, l, m, arr)
        self._build(2*node+1, m+1, r, arr)
        self.sum[node] = self.sum[2*node] + self.sum[2*node+1]

    def _apply(self, node, l, r, v):
        self.sum[node] += v * (r - l + 1)
        self.lazy[node] += v

    def _push(self, node, l, r):
        if self.lazy[node]:
            m = (l + r) // 2
            self._apply(2*node, l, m, self.lazy[node])
            self._apply(2*node+1, m+1, r, self.lazy[node])
            self.lazy[node] = 0

    def update(self, node, l, r, ql, qr, v):
        if qr < l or r < ql:
            return
        if ql <= l and r <= qr:
            self._apply(node, l, r, v)
            return
        self._push(node, l, r)
        m = (l + r) // 2
        self.update(2*node, l, m, ql, qr, v)
        self.update(2*node+1, m+1, r, ql, qr, v)
        self.sum[node] = self.sum[2*node] + self.sum[2*node+1]

    def query(self, node, l, r, ql, qr):
        if qr < l or r < ql:
            return 0
        self._push(node, l, r)
        if ql <= l and r <= qr:
            return self.sum[node]
        m = (l + r) // 2
        return self.query(2*node, l, m, ql, qr) + self.query(2*node+1, m+1, r, ql, qr)


if __name__ == "__main__":
    st = LazySegTree([1, 3, 5, 7, 9, 11])
    st.update(1, 0, 5, 1, 3, 5)          # add 5 to positions 1..3
    print(st.query(1, 0, 5, 1, 3))       # 36
