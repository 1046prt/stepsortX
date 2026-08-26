# sortsort · Kth Largest Element
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/kth-largest-element

import random

def quickselect(nums, k):
    # Returns kth largest by partitioning toward index n - k.
    target = len(nums) - k

    def select(left, right):
        pivot_index = random.randint(left, right)
        nums[pivot_index], nums[right] = nums[right], nums[pivot_index]
        pivot = nums[right]
        store = left
        for i in range(left, right):
            if nums[i] < pivot:
                nums[i], nums[store] = nums[store], nums[i]
                store += 1
        nums[store], nums[right] = nums[right], nums[store]
        if store == target:
            return nums[store]
        if store < target:
            return select(store + 1, right)
        return select(left, store - 1)

    return select(0, len(nums) - 1)


if __name__ == "__main__":
    nums = [3, 2, 1, 5, 6, 4]
    print("k=2 ->", quickselect(list(nums), 2))   # 5
    print("k=4 ->", quickselect(list(nums), 4))   # 3
