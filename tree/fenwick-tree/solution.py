# sortsort · Fenwick Tree (BIT)
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fenwick-tree

# Fenwick (Binary Indexed) Tree: prefix sums with point add

class FenwickTree:
    def __init__(self, values):
        self.n = len(values)
        self.tree = [0] * (self.n + 1)
        for i, v in enumerate(values):
            self.add(i + 1, v)

    def add(self, i, delta):
        # adds delta at 1-based index i, climbing to the next responsible cell
        while i <= self.n:
            self.tree[i] += delta
            i += i & (-i)

    def prefix_sum(self, i):
        # sum of elements at indices 1..i, peeling off lowest set bits
        total = 0
        while i > 0:
            total += self.tree[i]
            i -= i & (-i)
        return total

    def range_sum(self, l, r):
        # inclusive sum over indices l..r (1-based)
        return self.prefix_sum(r) - self.prefix_sum(l - 1)


if __name__ == "__main__":
    arr = [3, 2, -1, 6, 5, 4]
    ft = FenwickTree(arr)
    print("prefix sum to 3:", ft.prefix_sum(3))
    print("range sum 2..5:", ft.range_sum(2, 5))
    ft.add(4, 7)   # arr[3] += 7
    print("after adding 7 at index 4")
    print("prefix sum to 3:", ft.prefix_sum(3))
    print("prefix sum to 6:", ft.prefix_sum(6))
    print("range sum 2..5:", ft.range_sum(2, 5))
