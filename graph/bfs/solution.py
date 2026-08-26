# Stepsort · BFS
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bfs

from collections import deque


def bfs(graph, source):
    # Level-by-level traversal using a FIFO queue
    visited = [False] * len(graph)
    order = []
    queue = deque([source])
    visited[source] = True

    while queue:
        node = queue.popleft()
        order.append(node)
        for neighbor in graph[node]:
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)

    return order


if __name__ == "__main__":
    # Undirected graph with 6 vertices (0..5)
    graph = [
        [1, 2],
        [0, 3],
        [0, 3, 4],
        [1, 2, 5],
        [2],
        [3],
    ]
    print("BFS visit order from vertex 0:", bfs(graph, 0))
