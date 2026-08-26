# Stepsort · Ford-Fulkerson Max Flow
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-flow

from collections import deque


def ford_fulkerson(capacity, source, sink):
    # Ford-Fulkerson method; BFS picks shortest augmenting paths first
    n = len(capacity)
    residual = [row[:] for row in capacity]
    total_flow = 0
    while True:
        parent = [-1] * n
        parent[source] = source
        queue = deque([source])
        while queue and parent[sink] == -1:
            u = queue.popleft()
            for v in range(n):
                if residual[u][v] > 0 and parent[v] == -1:
                    parent[v] = u
                    queue.append(v)
        if parent[sink] == -1:
            break  # no augmenting path remains
        bottleneck = float("inf")
        v = sink
        while v != source:  # narrowest link limits the push
            bottleneck = min(bottleneck, residual[parent[v]][v])
            v = parent[v]
        v = sink
        while v != source:  # apply the push along the path
            u = parent[v]
            residual[u][v] -= bottleneck
            residual[v][u] += bottleneck
            v = u
        total_flow += bottleneck
    return total_flow


if __name__ == "__main__":
    capacity = [
        [0, 16, 13, 0, 0, 0],
        [0, 0, 10, 12, 0, 0],
        [0, 4, 0, 0, 14, 0],
        [0, 0, 9, 0, 0, 20],
        [0, 0, 0, 7, 0, 4],
        [0, 0, 0, 0, 0, 0],
    ]
    print("Max flow:", ford_fulkerson(capacity, 0, 5))
