# sortsort · Find Median from Data Stream
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-median-data-stream

import heapq

class MedianFinder:
    def __init__(self):
        self.small = []  # max heap via negated values
        self.large = []  # min heap

    def add_num(self, num):
        heapq.heappush(self.small, -num)
        heapq.heappush(self.large, -heapq.heappop(self.small))
        # keep small >= large in size
        if len(self.large) > len(self.small):
            heapq.heappush(self.small, -heapq.heappop(self.large))

    def find_median(self):
        if len(self.small) > len(self.large):
            return float(-self.small[0])
        return (-self.small[0] + self.large[0]) / 2


if __name__ == "__main__":
    finder = MedianFinder()
    for x in [5, 15, 1, 3]:
        finder.add_num(x)
        print("added", x, "-> median", finder.find_median())
