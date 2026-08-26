# sortsort · Sliding Window Maximum
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sliding-window-maximum

from collections import deque

def max_sliding_window(nums, k):
    # Deque holds indices whose values are decreasing; front is window max.
    dq = deque()
    result = []
    for i, num in enumerate(nums):
        while dq and nums[dq[-1]] <= num:
            dq.pop()
        dq.append(i)
        if dq[0] == i - k:  # front slid out of the window
            dq.popleft()
        if i >= k - 1:
            result.append(nums[dq[0]])
    return result


if __name__ == "__main__":
    print(max_sliding_window([1, 3, -1, -3, 5, 3, 6, 7], 3))
