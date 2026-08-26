# sortsort · Hopcroft-Karp
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hopcroft-karp

from collections import deque

INF = float("inf")


def hopcroft_karp(n_left, n_right, adj):
    """BFS layered phases + DFS augmentation; vertices are 1-based."""
    match_left = [0] * (n_left + 1)   # left u -> right v (0 = unmatched)
    match_right = [0] * (n_right + 1)
    dist = [0] * (n_left + 1)

    def bfs_layers():
        queue = deque()
        for u in range(1, n_left + 1):
            if match_left[u] == 0:
                dist[u] = 0
                queue.append(u)
            else:
                dist[u] = INF
        found_free = False
        while queue:
            u = queue.popleft()
            for v in adj[u]:
                w = match_right[v]
                if w == 0:
                    found_free = True
                elif dist[w] == INF:
                    dist[w] = dist[u] + 1
                    queue.append(w)
        return found_free

    def dfs_augment(u):
        for v in adj[u]:
            w = match_right[v]
            if w == 0 or (dist[w] == dist[u] + 1 and dfs_augment(w)):
                match_left[u] = v
                match_right[v] = u
                return True
        dist[u] = INF
        return False

    matching = 0
    while bfs_layers():
        for u in range(1, n_left + 1):
            if match_left[u] == 0 and dfs_augment(u):
                matching += 1
    return matching, match_left


if __name__ == "__main__":
    adj = {
        1: [1, 2],
        2: [1, 3],
        3: [2, 4],
        4: [3],
    }
    matching, match_left = hopcroft_karp(4, 4, adj)
    print("Maximum matching size:", matching)
    for u in range(1, len(match_left)):
        if match_left[u] != 0:
            print("Left", u, "matched with Right", match_left[u])
