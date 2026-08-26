# sortsort · Climbing Stairs
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/climbing-stairs

def climb_stairs(n):
    # Ways(n) follows Fibonacci; O(1) space iterative.
    prev, curr = 1, 1
    for _ in range(n - 1):
        prev, curr = curr, prev + curr
    return curr


if __name__ == "__main__":
    print(climb_stairs(10))  # 89
