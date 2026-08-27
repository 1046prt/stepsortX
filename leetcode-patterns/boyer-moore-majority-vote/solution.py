# Stepsort · Boyer-Moore Majority Vote
# Category: Arrays & Stacks
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore-majority-vote

def majority_element(nums):
    candidate, count = None, 0
    for num in nums:
        if count == 0:
            candidate = num
            count = 1
        elif num == candidate:
            count += 1
        else:
            count -= 1
    # Verify candidate is actually a majority
    if nums.count(candidate) > len(nums) // 2:
        return candidate
    return None

if __name__ == "__main__":
    print(majority_element([3, 3, 4, 2, 3, 3, 3]))  # 3
    print(majority_element([1, 2, 3]))                 # None
