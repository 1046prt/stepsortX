# sortsort · Min-Cost Max Flow
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-min-cost-flow

from collections import deque


class MinCostFlow:
    def __init__(self, n):
        self.n = n
        self.graph = [[] for _ in range(n)]  # edge: [to, cap, cost, rev]

    def add_edge(self, u, v, cap, cost):
        self.graph[u].append([v, cap, cost, len(self.graph[v])])
        self.graph[v].append([u, 0, -cost, len(self.graph[u]) - 1])

    def spfa(self, s):
        """Bellman-Ford with queue; handles negative residual costs."""
        INF = float("inf")
        dist = [INF] * self.n
        prev_node = [-1] * self.n
        prev_edge = [-1] * self.n
        in_queue = [False] * self.n
        dist[s] = 0
        queue = deque([s])
        in_queue[s] = True
        while queue:
            u = queue.popleft()
            in_queue[u] = False
            for i, edge in enumerate(self.graph[u]):
                to, cap, cost = edge[0], edge[1], edge[2]
                if cap > 0 and dist[u] + cost < dist[to]:
                    dist[to] = dist[u] + cost
                    prev_node[to] = u
                    prev_edge[to] = i
                    if not in_queue[to]:
                        in_queue[to] = True
                        queue.append(to)
        return dist, prev_node, prev_edge

    def min_cost_max_flow(self, s, t, max_push):
        flow = 0
        total_cost = 0
        INF = float("inf")
        while flow < max_push:
            dist, prev_node, prev_edge = self.spfa(s)
            if dist[t] == INF:
                break
            push = max_push - flow
            v = t
            while v != s:
                push = min(push, self.graph[prev_node[v]][prev_edge[v]][1])
                v = prev_node[v]
            v = t
            while v != s:
                edge = self.graph[prev_node[v]][prev_edge[v]]
                edge[1] -= push
                self.graph[v][edge[3]][1] += push
                v = prev_node[v]
            flow += push
            total_cost += push * dist[t]
        return flow, total_cost


if __name__ == "__main__":
    net = MinCostFlow(4)
    edges = [
        (0, 1, 2, 2),
        (0, 2, 1, 3),
        (1, 3, 1, 4),
        (1, 2, 1, 1),
        (2, 3, 2, 1),
    ]
    for u, v, cap, cost in edges:
        net.add_edge(u, v, cap, cost)
    flow, cost = net.min_cost_max_flow(0, 3, 100)
    print("Flow sent:", flow)
    print("Total cost:", cost)
