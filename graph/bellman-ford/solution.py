# sortsort · Bellman-Ford
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bellman-ford

def bellman_ford(num_vertices, edges, source):
    dist = [float("inf")] * num_vertices
    dist[source] = 0

    # Relax every edge V-1 times
    for _ in range(num_vertices - 1):
        changed = False
        for u, v, w in edges:
            if dist[u] != float("inf") and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                changed = True
        if not changed:
            break

    # One more improving pass means a negative cycle is reachable
    for u, v, w in edges:
        if dist[u] != float("inf") and dist[u] + w < dist[v]:
            return None

    return dist


if __name__ == "__main__":
    # Directed weighted graph with 5 vertices (0..4)
    edges = [(0, 1, 4), (0, 2, 5), (1, 2, -3), (1, 3, 6), (2, 3, 4), (3, 4, 2)]
    dist = bellman_ford(5, edges, 0)

    if dist is None:
        print("Negative cycle detected")
    else:
        print("Shortest distances from vertex 0:")
        for vertex, d in enumerate(dist):
            print(f"  vertex {vertex}: {'INF' if d == float('inf') else d}")
