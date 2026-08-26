# Stepsort · Prim's MST
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prim

import heapq


def prim(num_vertices, edges):
    graph = [[] for _ in range(num_vertices)]
    for u, v, w in edges:
        graph[u].append((w, v))
        graph[v].append((w, u))

    visited = [False] * num_vertices
    heap = [(0, 0, -1)]  # (edge weight, vertex, parent)
    total_weight = 0
    mst_edges = []

    while heap:
        weight, node, parent = heapq.heappop(heap)
        if visited[node]:
            continue  # stale queue entry
        visited[node] = True
        total_weight += weight
        if parent != -1:
            mst_edges.append((parent, node, weight))
        for w, neighbor in graph[node]:
            if not visited[neighbor]:
                heapq.heappush(heap, (w, neighbor, node))

    return total_weight, mst_edges


if __name__ == "__main__":
    # Undirected weighted graph with 5 vertices (0..4)
    edges = [(0, 1, 2), (0, 3, 6), (1, 2, 3),
             (1, 3, 8), (1, 4, 5), (2, 4, 7), (3, 4, 9)]

    total_weight, mst_edges = prim(5, edges)
    print("Prim MST total weight:", total_weight)
    print("MST edges (parent, child, weight):")
    for edge in mst_edges:
        print(" ", edge)
