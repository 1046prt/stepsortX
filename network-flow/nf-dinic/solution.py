# sortsort · Dinic's Algorithm
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-dinic

from collections import deque


class Dinic:
    def __init__(self, n):
        self.n = n
        self.graph = [[] for _ in range(n)]  # edge: [to, cap, rev_index]
        self.level = []
        self.iterator = []

    def add_edge(self, u, v, cap):
        self.graph[u].append([v, cap, len(self.graph[v])])
        self.graph[v].append([u, 0, len(self.graph[u]) - 1])

    def bfs_levels(self, s, t):
        self.level = [-1] * self.n
        self.level[s] = 0
        queue = deque([s])
        while queue:
            u = queue.popleft()
            for edge in self.graph[u]:
                if edge[1] > 0 and self.level[edge[0]] == -1:
                    self.level[edge[0]] = self.level[u] + 1
                    queue.append(edge[0])
        return self.level[t] != -1

    def dfs_blocking(self, u, t, limit):
        if u == t:
            return limit
        while self.iterator[u] < len(self.graph[u]):
            edge = self.graph[u][self.iterator[u]]
            to, cap, rev = edge
            if cap > 0 and self.level[to] == self.level[u] + 1:
                pushed = self.dfs_blocking(to, t, min(limit, cap))
                if pushed > 0:
                    edge[1] -= pushed
                    self.graph[to][rev][1] += pushed
                    return pushed
            self.iterator[u] += 1
        return 0

    def max_flow(self, s, t):
        flow = 0
        while self.bfs_levels(s, t):
            self.iterator = [0] * self.n
            pushed = self.dfs_blocking(s, t, float("inf"))
            while pushed > 0:
                flow += pushed
                pushed = self.dfs_blocking(s, t, float("inf"))
        return flow


if __name__ == "__main__":
    dinic = Dinic(6)
    edges = [
        (0, 1, 16), (0, 2, 13),
        (1, 3, 12),
        (2, 1, 4), (2, 4, 14),
        (3, 2, 9), (3, 5, 20),
        (4, 3, 7), (4, 5, 4),
    ]
    for u, v, c in edges:
        dinic.add_edge(u, v, c)
    print("Max flow:", dinic.max_flow(0, 5))
