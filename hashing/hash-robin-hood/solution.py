# Stepsort · Robin Hood Hashing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-robin-hood

CAP = 11


class Slot:
    def __init__(self, key=None, dist=-1):
        self.key = key
        self.dist = dist  # distance from ideal slot (-1 = empty)


class RobinHoodTable:
    def __init__(self):
        self.slots = [Slot() for _ in range(CAP)]

    def insert(self, key):
        entry = Slot(key, 0)
        pos = key % CAP
        while True:
            cur = self.slots[pos]
            if cur.dist == -1:
                self.slots[pos] = entry
                return entry.dist
            if cur.key == key:
                return -1  # duplicate ignored
            if cur.dist < entry.dist:  # resident closer to home: swap
                entry, self.slots[pos] = cur, entry
            entry.dist += 1
            pos = (pos + 1) % CAP

    def search(self, key):
        base = key % CAP
        for dist in range(CAP):
            cur = self.slots[(base + dist) % CAP]
            if cur.dist == -1 or cur.dist < dist:
                return -1  # early exit unique to robin hood
            if cur.key == key:
                return (base + dist) % CAP
        return -1


if __name__ == "__main__":
    rt = RobinHoodTable()
    for k in (10, 20, 30, 42, 52):
        d = rt.insert(k)
        print("insert", k, "-> settled at distance", d)
    for i, s in enumerate(rt.slots):
        if s.dist == -1:
            print("slot", i, "-> (empty)")
        else:
            print("slot", i, "-> key", s.key, "distance", s.dist)
    print("search 42 -> slot", rt.search(42))
    print("search 77 -> slot", rt.search(77), "(-1 = absent)")
