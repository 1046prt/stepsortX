# Stepsort · Multi-Source 0-1 BFS
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-source-01-bfs

from collections import deque

def multi_source_01_bfs(graph, sources):
    dist = [float("inf")] * len(graph)
    dq = deque()
    for s in sources:
        dist[s] = 0
        dq.appendleft(s)
    while dq:
        u = dq.popleft()
        for v, w in graph[u]:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                if w == 0:
                    dq.appendleft(v)
                else:
                    dq.append(v)
    return dist

graph = {
    0: [(1, 0), (2, 1)],
    1: [(3, 1)],
    2: [(3, 0), (4, 1)],
    3: [(5, 0)],
    4: [(5, 1)],
    5: [],
}
print(multi_source_01_bfs(graph, [0, 2]))
