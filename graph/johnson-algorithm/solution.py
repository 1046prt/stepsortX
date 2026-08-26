# Stepsort · Johnson's Algorithm
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/johnson-algorithm

import heapq


def bellman_ford(n, edges, source):
    dist = [float("inf")] * n
    dist[source] = 0
    for _ in range(n):  # at most n rounds of relaxation
        changed = False
        for u, v, w in edges:
            if dist[u] != float("inf") and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                changed = True
        if not changed:
            break
    for u, v, w in edges:
        if dist[u] != float("inf") and dist[u] + w < dist[v]:
            return None  # negative cycle detected
    return dist


def dijkstra(n, adj, source):
    dist = [float("inf")] * n
    dist[source] = 0
    heap = [(0, source)]
    while heap:
        d, u = heapq.heappop(heap)
        if d > dist[u]:
            continue
        for v, w in adj[u]:
            if d + w < dist[v]:
                dist[v] = d + w
                heapq.heappush(heap, (dist[v], v))
    return dist


def johnson(n, edges):
    # Virtual vertex n with 0-weight arcs feeds Bellman-Ford potentials
    extended = edges + [(n, v, 0) for v in range(n)]
    h = bellman_ford(n + 1, extended, n)
    if h is None:
        return None
    adj = [[] for _ in range(n)]
    for u, v, w in edges:
        adj[u].append((v, w + h[u] - h[v]))  # reweighted weights are >= 0
    result = [[float("inf")] * n for _ in range(n)]
    for s in range(n):
        dist = dijkstra(n, adj, s)
        for v in range(n):
            if dist[v] != float("inf"):
                result[s][v] = dist[v] - h[s] + h[v]  # undo the reweighting
    return result


if __name__ == "__main__":
    edges = [
        (0, 1, 3), (0, 2, 8), (0, 4, -4),
        (1, 3, 1), (1, 4, 7),
        (2, 1, 4),
        (3, 0, 2), (3, 2, -5),
        (4, 3, 6),
    ]
    matrix = johnson(5, edges)
    if matrix is None:
        print("Graph contains a negative weight cycle")
    else:
        print("All-pairs shortest path distances:")
        for row in matrix:
            print(row)
