# sortsort · Edmonds-Karp
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-edmonds-karp

from collections import deque


def edmonds_karp(n, capacity, source, sink):
    """BFS augmenting paths; each path uses the fewest edges available."""
    residual = [row[:] for row in capacity]
    max_flow = 0
    while True:
        parent = [-1] * n
        parent[source] = source
        queue = deque([source])
        while queue and parent[sink] == -1:
            u = queue.popleft()
            for v in range(n):
                if parent[v] == -1 and residual[u][v] > 0:
                    parent[v] = u
                    queue.append(v)
        if parent[sink] == -1:
            break
        # Walk the path backwards to find its bottleneck
        bottleneck = float("inf")
        v = sink
        while v != source:
            u = parent[v]
            bottleneck = min(bottleneck, residual[u][v])
            v = u
        # Apply the bottleneck along the path
        v = sink
        while v != source:
            u = parent[v]
            residual[u][v] -= bottleneck
            residual[v][u] += bottleneck
            v = u
        max_flow += bottleneck
    return max_flow


if __name__ == "__main__":
    n = 6
    capacity = [[0] * n for _ in range(n)]
    edges = [
        (0, 1, 16), (0, 2, 13),
        (1, 3, 12),
        (2, 1, 4), (2, 4, 14),
        (3, 2, 9), (3, 5, 20),
        (4, 3, 7), (4, 5, 4),
    ]
    for u, v, c in edges:
        capacity[u][v] = c
    print("Max flow:", edmonds_karp(n, capacity, 0, 5))
