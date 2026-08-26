# sortsort · Jump Game
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/jump-game

def can_jump(nums):
    # greedy: track the farthest index reachable so far
    farthest = 0
    for i, step in enumerate(nums):
        if i > farthest:
            return False
        farthest = max(farthest, i + step)
        if farthest >= len(nums) - 1:
            return True
    return True


if __name__ == "__main__":
    print(can_jump([2, 3, 1, 1, 4]))
    print(can_jump([3, 2, 1, 0, 4]))
    print(can_jump([0]))
