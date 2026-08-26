# sortsort · Single Number (XOR)
# Category: Bit Manipulation
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/single-number

def single_number(nums):
    # XOR of pairs cancels out, leaving the unique element
    result = 0
    for num in nums:
        result ^= num
    return result


if __name__ == "__main__":
    print(single_number([4, 1, 2, 1, 2]))
    print(single_number([2, 2, 1]))
    print(single_number([7]))
