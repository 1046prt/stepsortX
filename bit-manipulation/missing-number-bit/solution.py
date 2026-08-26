# sortsort · Missing Number (XOR)
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/missing-number-bit

def missing_number(nums):
    # XOR all values of 0..n with all array elements; pairs cancel
    n = len(nums)
    result = 0
    for v in range(n + 1):
        result ^= v
    for v in nums:
        result ^= v
    return result


if __name__ == "__main__":
    nums = [0, 1, 2, 4]
    print("nums:", nums)
    print("missing:", missing_number(nums))
