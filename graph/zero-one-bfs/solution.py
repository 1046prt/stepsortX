# Stepsort · 0-1 BFS
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/zero-one-bfs

from collections import deque


def zero_one_bfs(n, edges, src=0):
    adj = [[] for _ in range(n)]
    for u, v, w in edges:
        adj[u].append((v, w))
        adj[v].append((u, w))

    dist = [float("inf")] * n
    dist[src] = 0
    dq = deque([src])

    while dq:
        u = dq.popleft()
        for v, w in adj[u]:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                if w == 0:
                    dq.appendleft(v)
                else:
                    dq.append(v)
    return dist


if __name__ == "__main__":
    edges = [(0, 1, 0), (0, 2, 1), (1, 3, 0),
             (2, 3, 0), (2, 4, 1), (3, 5, 1), (4, 5, 0)]
    print(zero_one_bfs(6, edges))   # [0, 0, 1, 0, 2, 1]
