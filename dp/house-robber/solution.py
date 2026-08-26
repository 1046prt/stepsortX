# sortsort · House Robber
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/house-robber

def rob(houses: list[int]) -> int:
    # Max loot when adjacent houses cannot both be robbed,
    # using O(1) rolling variables.
    # prev2 = best up to house i-2, prev1 = best up to house i-1
    prev2, prev1 = 0, 0
    for money in houses:
        prev2, prev1 = prev1, max(prev1, prev2 + money)
    return prev1


if __name__ == "__main__":
    streets = [[2, 7, 9, 3, 1], [1, 2, 3, 1]]
    for street in streets:
        print("Houses:", street, "-> max loot:", rob(street))
