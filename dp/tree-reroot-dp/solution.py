# sortsort · Tree Rerooting DP
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-reroot-dp

def tree_reroot(n, edges):
    from collections import defaultdict
    adj = defaultdict(list)
    for u, v in edges:
        adj[u].append(v)
        adj[v].append(u)
    subtree = [0] * n
    answer = [0] * n

    def dfs1(u, p):
        subtree[u] = 1
        for v in adj[u]:
            if v != p:
                dfs1(v, u)
                subtree[u] += subtree[v]

    def dfs2(u, p):
        answer[u] = answer[p] + (n - subtree[u]) - subtree[u] if p != -1 else 0
        for v in adj[u]:
            if v != p:
                dfs2(v, u)

    dfs1(0, -1)
    answer[0] = sum(subtree[i] - 1 for i in range(n))
    dfs2(0, -1)
    return answer

edges = [(0,1),(0,2),(1,3),(1,4),(2,5),(2,6)]
print(tree_reroot(7, edges))
