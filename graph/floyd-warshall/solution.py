# sortsort · Floyd-Warshall
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/floyd-warshall

def floyd_warshall(matrix):
    # dist[i][j] = shortest path from i to j through intermediate vertices
    n = len(matrix)
    dist = [row[:] for row in matrix]
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if dist[i][k] + dist[k][j] < dist[i][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]
    return dist


def print_matrix(matrix):
    for row in matrix:
        cells = ["INF" if value == float("inf") else str(value) for value in row]
        print(" ".join(cells))


if __name__ == "__main__":
    # Directed weighted graph, INF = no direct edge
    inf = float("inf")
    matrix = [
        [0, 3, inf, 7],
        [8, 0, 2, inf],
        [5, inf, 0, 1],
        [2, inf, inf, 0],
    ]
    print("All-pairs shortest paths:")
    print_matrix(floyd_warshall(matrix))
