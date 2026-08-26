# sortsort · Heavy-Light Decomposition
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heavy-light-decomposition

def decompose(parent, children):
    n = len(parent)
    size = [1] * n
    chain_id = [-1] * n
    head = [-1] * n

    def depth_of(v):
        d = 0
        while parent[v] != -1:
            v = parent[v]
            d += 1
        return d

    # compute subtree sizes bottom-up
    for v in sorted(range(n), key=depth_of, reverse=True):
        for c in children.get(v, []):
            size[v] += size[c]

    chains = 0
    roots = [0]
    while roots:
        start = roots.pop()
        head[start] = start
        chain_id[start] = chains
        cur = start
        while children.get(cur):
            kids = children[cur]
            heavy = max(kids, key=lambda c: size[c])
            head[heavy] = head[cur]
            chain_id[heavy] = chains
            cur = heavy
            for c in kids:
                if c != heavy and chain_id[c] == -1:
                    roots.append(c)
        chains += 1
    return size, chain_id, head, chains


if __name__ == "__main__":
    parent = [-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7]
    children = {}
    for v, p in enumerate(parent):
        if p >= 0:
            children.setdefault(p, []).append(v)
    size, chain_id, head, chains = decompose(parent, children)
    print(f"{chains} chains:", chain_id)
