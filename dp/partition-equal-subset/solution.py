# Stepsort · Partition Equal Subset Sum
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/partition-equal-subset

def can_partition(nums: list[int]) -> bool:
    # True iff nums splits into two groups with equal sums.
    total = sum(nums)
    if total % 2 != 0:
        return False
    target = total // 2
    reachable = [False] * (target + 1)
    reachable[0] = True  # empty subset reaches sum 0
    for num in nums:
        # iterate sums downward so each num is used at most once
        for s in range(target, num - 1, -1):
            if reachable[s - num]:
                reachable[s] = True
    return reachable[target]


if __name__ == "__main__":
    print("[1, 5, 11, 5] partitionable:", can_partition([1, 5, 11, 5]))
    print("[1, 2, 3, 5] partitionable:", can_partition([1, 2, 3, 5]))
