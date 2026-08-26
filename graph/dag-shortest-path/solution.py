# Stepsort · DAG Shortest Path
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-shortest-path

from collections import deque


def dag_shortest_path(n, edges, src):
    adj = [[] for _ in range(n)]
    indeg = [0] * n
    for u, v, w in edges:
        adj[u].append((v, w))
        indeg[v] += 1

    q = deque([u for u in range(n) if indeg[u] == 0])
    topo = []
    while q:
        u = q.popleft()
        topo.append(u)
        for v, _ in adj[u]:
            indeg[v] -= 1
            if indeg[v] == 0:
                q.append(v)

    INF = float("inf")
    dist = [INF] * n
    dist[src] = 0
    for u in topo:
        if dist[u] == INF:
            continue
        for v, w in adj[u]:
            dist[v] = min(dist[v], dist[u] + w)
    return dist


if __name__ == "__main__":
    edges = [(0, 1, 3), (0, 2, 2), (1, 3, 4),
             (2, 3, -2), (2, 4, 5), (3, 5, 1), (4, 5, -1)]
    print(dag_shortest_path(6, edges, 0))   # [0, 3, 2, 4, 7, 5]
