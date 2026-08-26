# sortsort · Two Sum (Hash Map)
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-sum-hash

def two_sum(nums, target):
    seen = {}  # value -> index where it appeared
    for i, x in enumerate(nums):
        need = target - x
        if need in seen:
            return [seen[need], i]
        seen[x] = i
    return []


if __name__ == "__main__":
    nums = [2, 7, 11, 15, 3, 6]
    for target in (9, 18, 30):
        pair = two_sum(nums, target)
        if pair:
            a, b = pair
            print("target", target, "-> indices", a, b,
                  "values", nums[a], nums[b])
        else:
            print("target", target, "-> no pair found")
