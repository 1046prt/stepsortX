# Stepsort · Maximum Subarray
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-subarray

def max_subarray(nums):
    # Kadane O(n) scan while tracking the best window for reconstruction
    best = current = nums[0]
    start = end = temp_start = 0
    for i in range(1, len(nums)):
        if nums[i] > current + nums[i]:
            current = nums[i]
            temp_start = i
        else:
            current += nums[i]
        if current > best:
            best = current
            start = temp_start
            end = i
    return best, start, end


if __name__ == "__main__":
    nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    total, start, end = max_subarray(nums)
    print("array:", nums)
    print("max sum:", total)
    print("subarray:", nums[start:end + 1])
    print("range: indices", start, "to", end)
