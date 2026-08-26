# Stepsort · Chaining
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-chaining

class ChainHashTable:
    def __init__(self, capacity=7):
        self.capacity = capacity
        self.buckets = [[] for _ in range(capacity)]

    def _index(self, key):
        return key % self.capacity

    def insert(self, key, value):
        chain = self.buckets[self._index(key)]
        for pair in chain:
            if pair[0] == key:
                pair[1] = value
                return
        chain.append([key, value])

    def search(self, key):
        for k, v in self.buckets[self._index(key)]:
            if k == key:
                return v
        return None

    def delete(self, key):
        chain = self.buckets[self._index(key)]
        for i, pair in enumerate(chain):
            if pair[0] == key:
                chain.pop(i)
                return True
        return False


if __name__ == "__main__":
    ht = ChainHashTable(7)
    ht.insert(10, "A")
    ht.insert(17, "B")  # 10 % 7 == 3 and 17 % 7 == 3: same chain
    ht.insert(24, "C")  # third key in that chain
    ht.insert(5, "D")
    print("search 17 ->", ht.search(17))
    print("search 99 ->", ht.search(99))
    print("delete 17 ->", ht.delete(17))
    print("delete 99 ->", ht.delete(99))
    print("search 17 after delete ->", ht.search(17))
    for i, chain in enumerate(ht.buckets):
        print("bucket", i, "->", chain if chain else "(empty)")
