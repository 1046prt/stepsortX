# Stepsort · Next Greater Element
# Category: Arrays & Stacks
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/next-greater-element

def next_greater_element(nums):
    n = len(nums)
    result = [-1] * n
    stack = []  # stores indices
    for i in range(n - 1, -1, -1):
        while stack and nums[stack[-1]] <= nums[i]:
            stack.pop()
        if stack:
            result[i] = nums[stack[-1]]
        stack.append(i)
    return result

if __name__ == "__main__":
    print(next_greater_element([4, 5, 2, 25]))
    # [5, 25, 25, -1]
