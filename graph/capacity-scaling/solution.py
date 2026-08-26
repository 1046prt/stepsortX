# Stepsort · Capacity Scaling Max Flow
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/capacity-scaling

INF = float("inf")


def capacity_scaling(n, cap, src=0, sink=5):
    flow = [[0] * n for _ in range(n)]
    max_cap = max(max(row) for row in cap)
    delta = 1
    while delta * 2 <= max_cap:
        delta *= 2

    total = 0
    log = []

    def dfs(u, limit, visited):
        if u == sink:
            return limit
        visited.add(u)
        for v in range(n):
            if v in visited:
                continue
            residual = cap[u][v] - flow[u][v]
            if residual >= limit:
                pushed = dfs(v, min(limit, residual), visited)
                if pushed > 0:
                    flow[u][v] += pushed
                    flow[v][u] -= pushed
                    return pushed
        return 0

    while delta >= 1:
        while True:
            pushed = dfs(src, delta, set())
            if pushed == 0:
                break
            total += pushed
            log.append(f"delta={delta}: +{pushed}")
        delta //= 2
    return total, log


if __name__ == "__main__":
    cap = [[0]*6 for _ in range(6)]
    for a, b, c in [(0,1,16),(0,2,13),(1,3,12),(2,1,4),(3,2,9),(2,4,14),(4,3,7),(3,5,20),(4,5,4)]:
        cap[a][b] = c
    total, log = capacity_scaling(6, cap)
    for entry in log:
        print(entry)
    print("max flow:", total)   # 23
