# sortsort · Kosaraju's SCC
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kosaraju-scc

from collections import defaultdict


def dfs_finish_order(graph, v, visited, order):
    # First pass on G: record vertices by DFS finish time
    visited[v] = True
    for u in graph[v]:
        if not visited[u]:
            dfs_finish_order(graph, u, visited, order)
    order.append(v)


def dfs_collect(graph, v, visited, component):
    # Second pass on reversed G: gather one component
    visited[v] = True
    component.append(v)
    for u in graph[v]:
        if not visited[u]:
            dfs_collect(graph, u, visited, component)


def kosaraju_scc(num_vertices, edges):
    graph = defaultdict(list)
    reverse_graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        reverse_graph[v].append(u)
    visited = [False] * num_vertices
    order = []
    for v in range(num_vertices):
        if not visited[v]:
            dfs_finish_order(graph, v, visited, order)
    visited = [False] * num_vertices
    components = []
    for v in reversed(order):  # decreasing finish time
        if not visited[v]:
            component = []
            dfs_collect(reverse_graph, v, visited, component)
            components.append(sorted(component))
    return components


if __name__ == "__main__":
    edges = [(1, 0), (0, 2), (2, 1), (0, 3), (3, 4)]
    print("Strongly connected components:")
    for component in kosaraju_scc(5, edges):
        print(component)
