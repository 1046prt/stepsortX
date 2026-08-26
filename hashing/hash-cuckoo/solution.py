# Stepsort · Cuckoo Hashing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-cuckoo

MAX_KICKS = 8  # eviction budget before a rehash


class CuckooTable:
    def __init__(self, cap=4):
        self.cap = cap
        self.t1 = [None] * cap
        self.t2 = [None] * cap

    def h1(self, key):
        return key % self.cap

    def h2(self, key):
        return (key // self.cap) % self.cap

    def lookup(self, key):
        return self.t1[self.h1(key)] == key or self.t2[self.h2(key)] == key

    def insert(self, key):
        for _ in range(MAX_KICKS):
            pos = self.h1(key)
            if self.t1[pos] is None:
                self.t1[pos] = key
                return
            key, self.t1[pos] = self.t1[pos], key  # evict occupant
            print("  kick", key, "out of T1 slot", pos)
            pos = self.h2(key)
            if self.t2[pos] is None:
                self.t2[pos] = key
                return
            key, self.t2[pos] = self.t2[pos], key
            print("  kick", key, "out of T2 slot", pos)
        print("  eviction cycle limit hit: rehashing")
        self.rehash()
        self.insert(key)

    def rehash(self):
        old = [k for k in self.t1 + self.t2 if k is not None]
        self.cap *= 2
        self.t1 = [None] * self.cap
        self.t2 = [None] * self.cap
        for k in old:
            self.insert(k)


if __name__ == "__main__":
    ct = CuckooTable()
    for k in (4, 8, 12, 1, 5):
        print("insert", k)
        ct.insert(k)
    print("T1:", ct.t1)
    print("T2:", ct.t2)
    for k in (4, 8, 12, 1, 5, 99):
        print("lookup", k, "->", ct.lookup(k))
