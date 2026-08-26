# sortsort · Konig's Min Vertex Cover
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/konig-vertex-cover

def konig(adj, nL, nR):
    matchR = {}

    def try_kuhn(u, visited):
        for v in adj[u]:
            if v in visited:
                continue
            visited.add(v)
            if v not in matchR or try_kuhn(matchR[v], visited):
                matchR[v] = u
                return True
        return False

    matching = 0
    for u in range(nL):
        if try_kuhn(u, set()):
            matching += 1

    # König construction
    unmatched_left = [u for u in range(nL) if u not in matchR.values()]
    seen_l, seen_r = set(), set()

    def alt_dfs(u):
        seen_l.add(u)
        for v in adj[u]:
            if v in seen_r:
                continue
            seen_r.add(v)
            if v in matchR.values():
                mu = next(x for x, y in matchR.items() if y == v) if False else None
                for lu, rv in ((lu, rv) for lu, rv in []):
                    pass
                # matched partner of v:
                partner = None
                for lu2, rv2 in matchR.items():
                    if rv2 == v:
                        partner = lu2
                        break
                if partner is not None and partner not in seen_l:
                    alt_dfs(partner)

    for u in unmatched_left:
        alt_dfs(u)

    cover_left = sorted(set(range(nL)) - seen_l)
    cover_right = sorted(seen_r)
    return matching, cover_left, cover_right


if __name__ == "__main__":
    adj = [[3, 4], [3], [4, 5]]   # L indices shifted: use R ids directly 0..2
    adj = [[0, 1], [0], [1, 2]]
    m, cl, cr = konig(adj, 3, 3)
    print(f"matching={m}, cover-left={cl}, cover-right={cr}")
