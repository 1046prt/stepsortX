# sortsort · Find Missing Number
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/find-missing-number

def missing_number(nums):
    # Sum of 0..n minus the sum of the array reveals the missing value.
    n = len(nums)
    expected = n * (n + 1) // 2
    return expected - sum(nums)


if __name__ == "__main__":
    print(missing_number([3, 0, 1]))
    print(missing_number([9, 6, 4, 2, 3, 5, 7, 0, 1]))
