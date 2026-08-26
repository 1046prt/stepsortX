# Stepsort · Optimal Merge Pattern
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/optimal-merge

import heapq


def optimal_merge_cost(file_sizes):
    # always combine the two smallest files first
    heap = list(file_sizes)
    heapq.heapify(heap)
    total_cost = 0
    while len(heap) > 1:
        first = heapq.heappop(heap)
        second = heapq.heappop(heap)
        cost = first + second
        total_cost += cost
        heapq.heappush(heap, cost)
    return total_cost


if __name__ == "__main__":
    sizes = [4, 3, 2, 6]
    print("Minimum merge cost:", optimal_merge_cost(sizes))
