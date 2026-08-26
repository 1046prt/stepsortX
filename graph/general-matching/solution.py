# Stepsort · General Graph Matching
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/general-matching

def blossom_match(n, edges):
    match = [-1] * n
    label = [0] * n
    parent = [-1] * n
    base = list(range(n))

    def lca(a, b):
        seen = set()
        while True:
            a = base[a]
            seen.add(a)
            if match[a] == -1: break
            a = parent[match[a]]
        while True:
            b = base[b]
            if b in seen: return b
            b = parent[match[b]]

    def mark_path(v, b, child):
        while base[v] != b:
            label[base[v]] = label[base[match[v]]] = 1
            parent[v] = child
            child = match[v]
            v = parent[match[v]]

    def find_augment(root):
        parent = [-1] * n
        label = [0] * n
        base = list(range(n))
        q = [root]
        label[root] = 1
        head = 0
        while head < len(q):
            u = q[head]; head += 1
            for v in edges[u]:
                if label[v] == 0:
                    label[v] = 2; parent[v] = u
                    if match[v] == -1:
                        while v != -1: match[v], v = parent[v], match[parent[v]]
                        return True
                    label[match[v]] = 1; q.append(match[v])
                elif label[v] == 1:
                    b = lca(u, v)
                    mark_path(u, b, v); mark_path(v, b, u)
        return False

    result = 0
    for i in range(n):
        if match[i] == -1 and find_augment(i):
            result += 1
    return result

edges = {0:[1,2], 1:[0,3], 2:[0,3], 3:[1,2,4], 4:[3,5], 5:[4]}
print("Max matching:", blossom_match(6, edges))
