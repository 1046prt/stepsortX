# Stepsort · DAG Dynamic Programming
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dag-dp

from collections import defaultdict

def dag_longest_path(n, edges):
    adj = defaultdict(list)
    indeg = [0] * n
    for u, v, w in edges:
        adj[u].append((v, w))
        indeg[v] += 1
    topo = []
    queue = [i for i in range(n) if indeg[i] == 0]
    while queue:
        u = queue.pop()
        topo.append(u)
        for v, w in adj[u]:
            indeg[v] -= 1
            if indeg[v] == 0:
                queue.append(v)
    dist = [-float("inf")] * n
    for i in range(n):
        if indeg[i] == 0 and dist[i] == -float("inf"):
            dist[i] = 0
    for u in topo:
        for v, w in adj[u]:
            dist[v] = max(dist[v], dist[u] + w)
    return dist

edges = [(0,1,5),(0,2,3),(1,3,6),(1,2,2),(2,4,4),(3,5,2),(4,5,1)]
print(dag_longest_path(6, edges))
