# sortsort · Container With Most Water
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/two-pointers

def max_area(height):
    # Two pointers at the ends; always move the shorter wall inward.
    left, right = 0, len(height) - 1
    best = 0
    while left < right:
        area = min(height[left], height[right]) * (right - left)
        best = max(best, area)
        if height[left] < height[right]:
            left += 1
        else:
            right -= 1
    return best


if __name__ == "__main__":
    print(max_area([1, 8, 6, 2, 5, 4, 8, 3, 7]))
