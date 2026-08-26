# Stepsort · Heap Insert
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-insert

# Max-heap insert on an array with sift-up

class MaxHeap:
    def __init__(self):
        self.data = []

    def _sift_up(self, i):
        # move the value at i up while it is larger than its parent
        while i > 0:
            parent = (i - 1) // 2
            if self.data[i] <= self.data[parent]:
                break
            self.data[i], self.data[parent] = self.data[parent], self.data[i]
            i = parent

    def insert(self, value):
        # append at the end, then sift up to restore heap order
        self.data.append(value)
        self._sift_up(len(self.data) - 1)


if __name__ == "__main__":
    heap = MaxHeap()
    for value in [15, 12, 20, 8, 25, 18, 30, 5]:
        heap.insert(value)
        print("inserted", value, "-> array:", heap.data)
    print("max element sits at index 0:", heap.data[0])
