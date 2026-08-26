# sortsort · Skip List
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-skip-list

import random

MAX_LEVEL = 4  # cap on tower height
P = 0.5        # probability of growing one extra level


class Node:
    def __init__(self, key, level):
        self.key = key
        self.forward = [None] * (level + 1)  # next node at each level


class SkipList:
    def __init__(self):
        self.header = Node(float("-inf"), MAX_LEVEL)
        self.level = 0

    def _random_level(self):
        lvl = 0
        while random.random() < P and lvl < MAX_LEVEL:
            lvl += 1
        return lvl

    def _predecessors(self, key):
        update = [self.header] * (MAX_LEVEL + 1)
        node = self.header
        for i in range(self.level, -1, -1):
            while node.forward[i] and node.forward[i].key < key:
                node = node.forward[i]
            update[i] = node
        return update

    def search(self, key):
        candidate = self._predecessors(key)[0].forward[0]
        return candidate is not None and candidate.key == key

    def insert(self, key):
        update = self._predecessors(key)
        candidate = update[0].forward[0]
        if candidate is not None and candidate.key == key:
            return False  # duplicate keys are ignored
        lvl = self._random_level()
        self.level = max(self.level, lvl)
        node = Node(key, lvl)
        for i in range(lvl + 1):
            node.forward[i] = update[i].forward[i]
            update[i].forward[i] = node
        return True

    def delete(self, key):
        update = self._predecessors(key)
        target = update[0].forward[0]
        if target is None or target.key != key:
            return False
        for i in range(self.level + 1):
            if update[i].forward[i] is target:
                update[i].forward[i] = target.forward[i]
        while self.level > 0 and self.header.forward[self.level] is None:
            self.level -= 1
        return True


if __name__ == "__main__":
    random.seed(42)
    sl = SkipList()
    for value in [10, 30, 20, 50, 40]:
        sl.insert(value)
    print("search 20 ->", sl.search(20))
    print("search 60 ->", sl.search(60))
    sl.delete(30)
    print("search 30 after delete ->", sl.search(30))
    node = sl.header.forward[0]
    while node is not None:
        print("key:", node.key, "height:", len(node.forward) - 1)
        node = node.forward[0]
