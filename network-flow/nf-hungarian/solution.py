# Stepsort · Hungarian Algorithm
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hungarian

def hungarian(cost):
    """O(n^3) JV-style Hungarian method; minimizes total assignment cost."""
    n = len(cost)
    INF = float("inf")
    u = [0] * (n + 1)      # row potentials
    v = [0] * (n + 1)      # column potentials
    p = [0] * (n + 1)      # p[j]: row matched to column j (1-based)
    way = [0] * (n + 1)
    for i in range(1, n + 1):
        p[0] = i
        col = 0
        min_v = [INF] * (n + 1)
        used = [False] * (n + 1)
        while True:
            used[col] = True
            row = p[col]
            delta = INF
            next_col = -1
            for j in range(1, n + 1):
                if not used[j]:
                    reduced = cost[row - 1][j - 1] - u[row] - v[j]
                    if reduced < min_v[j]:
                        min_v[j] = reduced
                        way[j] = col
                    if min_v[j] < delta:
                        delta = min_v[j]
                        next_col = j
            for j in range(n + 1):
                if used[j]:
                    u[p[j]] += delta
                    v[j] -= delta
                else:
                    min_v[j] -= delta
            col = next_col
            if p[col] == 0:
                break
        # Flip the alternating path so row i gets matched
        while col:
            prev = way[col]
            p[col] = p[prev]
            col = prev
    assignment = [0] * n
    for j in range(1, n + 1):
        assignment[p[j] - 1] = j - 1
    total = sum(cost[i][assignment[i]] for i in range(n))
    return total, assignment


if __name__ == "__main__":
    cost = [
        [9, 2, 7],
        [6, 4, 3],
        [5, 8, 1],
    ]
    total, assignment = hungarian(cost)
    print("Minimum total cost:", total)
    for row, col in enumerate(assignment):
        print("Worker", row, "-> Job", col, "(cost", cost[row][col], ")")
