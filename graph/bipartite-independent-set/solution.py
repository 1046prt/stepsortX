# Stepsort · Bipartite Independent Set
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bipartite-independent-set

def bipartite_independent_set(n, adj, left_nodes):
    match = [-1] * n
    def bpm(u, seen):
        for v in adj[u]:
            if not seen[v]:
                seen[v] = True
                if match[v] == -1 or bpm(match[v], seen):
                    match[v] = u
                    return True
        return False
    result = 0
    for u in left_nodes:
        seen = [False] * n
        if bpm(u, seen): result += 1
    unmatched = [i for i in range(n) if match[i] == -1 and i in left_nodes]
    matched_left = set(match[v] for v in range(n) if match[v] != -1)
    matched_right = set(v for v in range(n) if match[v] != -1)
    independent = unmatched.copy()
    for v in range(n):
        if v not in matched_right:
            independent.append(v)
    return result, independent

adj = {0:[3,4], 1:[3], 2:[4], 5:[]}
left = [0, 1, 2, 5]
size, independent = bipartite_independent_set(6, adj, left)
print(f"Max matching: {size}, Independent set: {independent}")
