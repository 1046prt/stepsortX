# sortsort · Subsets (Power Set)
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subsets

def subsets(nums):
    result = []

    def backtrack(start, current):
        result.append(current[:])  # every prefix is a valid subset
        for i in range(start, len(nums)):
            current.append(nums[i])
            backtrack(i + 1, current)
            current.pop()

    backtrack(0, [])
    return result


if __name__ == "__main__":
    for subset in subsets([1, 2, 3]):
        print(subset)
