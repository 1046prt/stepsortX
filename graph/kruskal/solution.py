# Stepsort · Kruskal's MST
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kruskal

class UnionFind:
    def __init__(self, size):
        self.parent = list(range(size))

    def find(self, x):
        while self.parent[x] != x:
            self.parent[x] = self.parent[self.parent[x]]  # path compression
            x = self.parent[x]
        return x

    def union(self, a, b):
        root_a = self.find(a)
        root_b = self.find(b)
        if root_a == root_b:
            return False
        self.parent[root_a] = root_b
        return True


def kruskal(num_vertices, edges):
    uf = UnionFind(num_vertices)
    total_weight = 0
    chosen = []
    for u, v, w in sorted(edges, key=lambda edge: edge[2]):
        if uf.union(u, v):  # skip edges that would form a cycle
            total_weight += w
            chosen.append((u, v, w))
    return total_weight, chosen


if __name__ == "__main__":
    # Undirected weighted graph with 4 vertices (0..3)
    edges = [(0, 1, 4), (0, 2, 3), (1, 2, 1), (1, 3, 2), (2, 3, 5)]

    total_weight, chosen = kruskal(4, edges)
    print("Kruskal MST total weight:", total_weight)
    print("Chosen edges (u, v, weight):")
    for edge in chosen:
        print(" ", edge)
