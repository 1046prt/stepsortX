# Stepsort · DFS
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dfs

def dfs(graph, source):
    # Recursive depth-first exploration
    visited = [False] * len(graph)
    order = []

    def explore(node):
        visited[node] = True
        order.append(node)
        for neighbor in graph[node]:
            if not visited[neighbor]:
                explore(neighbor)

    explore(source)
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
    print("DFS visit order from vertex 0:", dfs(graph, 0))
