# sortsort · Egg Drop Problem
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/egg-drop

def egg_drop(eggs: int, floors: int) -> int:
    # dp[e][f] = minimum trials needed with e eggs and f floors
    dp = [[0] * (floors + 1) for _ in range(eggs + 1)]
    for f in range(1, floors + 1):
        dp[1][f] = f  # single egg: must try every floor linearly
    for e in range(2, eggs + 1):
        for f in range(1, floors + 1):
            best = float("inf")
            for x in range(1, f + 1):  # drop from floor x
                worst = max(dp[e - 1][x - 1], dp[e][f - x])
                best = min(best, 1 + worst)
            dp[e][f] = best
    return dp[eggs][floors]


if __name__ == "__main__":
    print("Egg drop with 2 eggs, 10 floors:", egg_drop(2, 10))
