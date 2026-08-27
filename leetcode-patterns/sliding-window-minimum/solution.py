# Stepsort · Sliding Window Minimum
# Category: Arrays & Stacks
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-minimum

from collections import deque

def sliding_window_minimum(nums, k):
    dq = deque()  # stores indices
    result = []
    for i, num in enumerate(nums):
        while dq and dq[0] < i - k + 1:
            dq.popleft()
        while dq and nums[dq[-1]] >= num:
            dq.pop()
        dq.append(i)
        if i >= k - 1:
            result.append(nums[dq[0]])
    return result

if __name__ == "__main__":
    print(sliding_window_minimum([1,3,-1,-3,5,3,6,7], 3))
    # [1, -1, -3, 3, 3]
