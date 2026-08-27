# Stepsort · SPFA (Shortest Path Faster)
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/spfa

from collections import deque

def spfa(graph, source, n):
    INF = float('inf')
    dist = [INF] * n
    in_queue = [False] * n
    enqueue_count = [0] * n
    dist[source] = 0
    queue = deque([source])
    in_queue[source] = True
    enqueue_count[source] = 1

    while queue:
        u = queue.popleft()
        in_queue[u] = False
        for v, w in graph[u]:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                if not in_queue[v]:
                    queue.append(v)
                    in_queue[v] = True
                    enqueue_count[v] += 1
                    if enqueue_count[v] > n:
                        return None  # negative cycle detected
    return dist

if __name__ == "__main__":
    n = 5
    graph = [[] for _ in range(n)]
    for u, v, w in [(0,1,6),(0,2,4),(1,2,2),(1,3,5),(2,3,-3),(2,4,1),(3,4,2)]:
        graph[u].append((v, w))
    print("distances:", spfa(graph, 0, n))
