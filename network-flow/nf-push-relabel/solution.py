# sortsort · Push-Relabel
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-push-relabel

from collections import deque


def push_relabel(n, capacity, s, t):
    """Generic FIFO preflow-push with height labels; excess[t] is the answer."""
    residual = [row[:] for row in capacity]
    height = [0] * n
    excess = [0] * n
    active = [False] * n
    height[s] = n
    queue = deque()

    def enqueue(v):
        if v != s and v != t and not active[v] and excess[v] > 0:
            active[v] = True
            queue.append(v)

    # Saturate every edge leaving the source
    for v in range(n):
        if residual[s][v] > 0:
            excess[v] += residual[s][v]
            residual[v][s] += residual[s][v]
            residual[s][v] = 0
            enqueue(v)

    while queue:
        u = queue.popleft()
        active[u] = False
        # Discharge u: push then relabel as needed
        while excess[u] > 0:
            moved = False
            for v in range(n):
                if residual[u][v] > 0 and height[u] == height[v] + 1:
                    amount = min(excess[u], residual[u][v])
                    residual[u][v] -= amount
                    residual[v][u] += amount
                    excess[u] -= amount
                    excess[v] += amount
                    enqueue(v)
                    moved = True
                    if excess[u] == 0:
                        break
            if not moved:
                lowest = min(height[v] for v in range(n) if residual[u][v] > 0)
                height[u] = lowest + 1
    return excess[t]


if __name__ == "__main__":
    n = 6
    capacity = [[0] * n for _ in range(n)]
    edges = [
        (0, 1, 16), (0, 2, 13),
        (1, 3, 12),
        (2, 1, 4), (2, 4, 14),
        (3, 2, 9), (3, 5, 20),
        (4, 3, 7), (4, 5, 4),
    ]
    for u, v, c in edges:
        capacity[u][v] = c
    print("Max flow:", push_relabel(n, capacity, 0, 5))
