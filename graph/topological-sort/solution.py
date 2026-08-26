# Stepsort · Topological Sort
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/topological-sort

from collections import deque


def topological_sort(vertices, adj):
    # Kahn BFS method: repeatedly remove vertices whose indegree hits zero
    indegree = [0] * vertices
    for u in range(vertices):
        for v in adj[u]:
            indegree[v] += 1
    queue = deque(u for u in range(vertices) if indegree[u] == 0)
    order = []
    while queue:
        u = queue.popleft()
        order.append(u)
        for v in adj[u]:
            indegree[v] -= 1
            if indegree[v] == 0:
                queue.append(v)
    return order  # fewer than vertices in order means the graph had a cycle


if __name__ == "__main__":
    V = 6
    adj = [[] for _ in range(V)]
    for u, v in [(5, 2), (5, 0), (4, 0), (4, 1), (2, 3), (3, 1)]:
        adj[u].append(v)
    order = topological_sort(V, adj)
    print("Topological order:", order)
    print("Valid DAG ordering:", len(order) == V)
