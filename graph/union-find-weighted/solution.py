# Stepsort · Weighted Union-Find
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/union-find-weighted

class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0] * n
        self.components = n

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])  # path compression
        return self.parent[x]

    def union(self, x, y):
        rx, ry = self.find(x), self.find(y)
        if rx == ry:
            return False
        if self.rank[rx] < self.rank[ry]:
            rx, ry = ry, rx
        self.parent[ry] = rx
        if self.rank[rx] == self.rank[ry]:
            self.rank[rx] += 1
        self.components -= 1
        return True

    def connected(self, x, y):
        return self.find(x) == self.find(y)

if __name__ == "__main__":
    uf = UnionFind(6)
    uf.union(0, 1); uf.union(2, 3); uf.union(1, 3)
    print("0 and 3 connected?", uf.connected(0, 3))  # True
    print("0 and 4 connected?", uf.connected(0, 4))  # False
