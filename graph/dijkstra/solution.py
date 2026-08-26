# sortsort · Dijkstra's
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dijkstra

import heapq


def dijkstra(num_vertices, edges, source):
    graph = [[] for _ in range(num_vertices)]
    for u, v, w in edges:
        graph[u].append((v, w))
        graph[v].append((u, w))

    dist = [float("inf")] * num_vertices
    dist[source] = 0
    heap = [(0, source)]  # (distance, vertex)

    while heap:
        d, node = heapq.heappop(heap)
        if d > dist[node]:
            continue  # stale queue entry
        for neighbor, weight in graph[node]:
            new_dist = d + weight
            if new_dist < dist[neighbor]:
                dist[neighbor] = new_dist
                heapq.heappush(heap, (new_dist, neighbor))

    return dist


if __name__ == "__main__":
    # Undirected weighted graph with 5 vertices (0..4)
    edges = [(0, 1, 4), (0, 2, 1), (2, 1, 2), (1, 3, 5), (2, 3, 8), (3, 4, 3)]
    dist = dijkstra(5, edges, 0)

    print("Shortest distances from vertex 0:")
    for vertex, d in enumerate(dist):
        print(f"  vertex {vertex}: {'INF' if d == float('inf') else d}")
