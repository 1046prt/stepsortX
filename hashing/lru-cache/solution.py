# sortsort · LRU Cache
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lru-cache

class Node:
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity):
        self.capacity = capacity
        self.map = {}
        self.head = Node()  # dummy: most recent side
        self.tail = Node()  # dummy: least recent side
        self.head.next = self.tail
        self.tail.prev = self.head

    def _remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _push_front(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key):
        if key not in self.map:
            return -1
        node = self.map[key]
        self._remove(node)
        self._push_front(node)
        return node.value

    def put(self, key, value):
        if key in self.map:
            node = self.map[key]
            node.value = value
            self._remove(node)
            self._push_front(node)
            return
        if len(self.map) >= self.capacity:
            lru = self.tail.prev
            self._remove(lru)
            del self.map[lru.key]
            print("  evict key", lru.key)
        node = Node(key, value)
        self.map[key] = node
        self._push_front(node)


if __name__ == "__main__":
    cache = LRUCache(2)
    cache.put(1, 100)
    cache.put(2, 200)
    print("get 1 ->", cache.get(1))   # refreshes key 1
    cache.put(3, 300)                 # capacity exceeded: evicts key 2
    print("get 2 ->", cache.get(2))
    cache.put(4, 400)                 # evicts key 1
    print("get 1 ->", cache.get(1))
    print("get 3 ->", cache.get(3))
