# sortsort · Hamiltonian Path
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamiltonian-path

def hamiltonian_path(adj_matrix):
    n = len(adj_matrix)
    path = []
    visited = [False] * n

    def backtrack(count, last):
        if count == n:
            return True  # every vertex used exactly once
        for v in range(n):
            if visited[v]:
                continue
            if count > 0 and adj_matrix[last][v] == 0:
                continue  # must be adjacent to the previous vertex
            visited[v] = True
            path.append(v)
            if backtrack(count + 1, v):
                return True
            path.pop()
            visited[v] = False
        return False

    return path if backtrack(0, -1) else None


if __name__ == "__main__":
    adj_matrix = [
        [0, 1, 0, 1],
        [1, 0, 1, 1],
        [0, 1, 0, 1],
        [1, 1, 1, 0],
    ]
    result = hamiltonian_path(adj_matrix)
    print("Hamiltonian path:", result if result is not None else "none")
