# Stepsort · Offline Dynamic Connectivity
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/offline-dynamic-connectivity

class RollbackDSU:
    def __init__(self, n):
        self.parent = list(range(n))
        self.size = [1] * n
        self.log = []

    def find(self, x):
        while self.parent[x] != x:
            x = self.parent[x]
        return x

    def unite(self, a, b):
        ra, rb = self.find(a), self.find(b)
        if ra == rb:
            self.log.append(-1)
            return False
        if self.size[ra] < self.size[rb]:
            ra, rb = rb, ra
        self.parent[rb] = ra
        self.size[ra] += self.size[rb]
        self.log.append(rb)
        return True

    def rollback(self, mark):
        while len(self.log) > mark:
            rb = self.log.pop()
            if rb != -1:
                self.size[self.parent[rb]] -= self.size[rb]
                self.parent[rb] = rb

    def components(self, n):
        roots = {self.find(v) for v in range(n)}
        return len(roots)


if __name__ == "__main__":
    n = 5
    lifespan = [(0, 1, 0, 6), (1, 2, 0, 3), (2, 3, 2, 6), (0, 3, 1, 4), (3, 4, 4, 6)]
    dsu = RollbackDSU(n)
    for t in range(6):
        mark = len(dsu.log)
        for u, v, s, e in lifespan:
            if s <= t < e:
                dsu.unite(u, v)
        print(f"t={t}: {dsu.components(n)} components")
        dsu.rollback(mark)
