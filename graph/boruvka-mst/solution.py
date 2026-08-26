# sortsort · Boruvka's MST
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boruvka-mst

def boruvka_mst(n, edges):
    parent = list(range(n))

    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x

    total = 0
    chosen = []
    components = n
    round_ = 0

    while components > 1:
        round_ += 1
        cheapest = {}
        for u, v, w in edges:
            ru, rv = find(u), find(v)
            if ru == rv:
                continue
            for r in (ru, rv):
                if r not in cheapest or w < cheapest[r][2]:
                    cheapest[r] = (u, v, w)
        merged = 0
        seen_pairs = set()
        for r in sorted(cheapest):
            u, v, w = cheapest[r]
            ru, rv = find(u), find(v)
            if ru == rv:
                continue
            pair = (min(ru, rv), max(ru, rv))
            if pair in seen_pairs:
                continue
            seen_pairs.add(pair)
            parent[ru] = rv
            components -= 1
            total += w
            chosen.append((u, v, w))
            merged += 1
        if merged == 0:
            break
    return total, chosen


if __name__ == "__main__":
    edges = [(0,1,4),(0,2,2),(1,2,1),(1,3,5),(2,4,10),(3,4,2),(3,5,6),(4,5,3)]
    total, chosen = boruvka_mst(6, edges)
    print(f"MST weight: {total}")   # 20
