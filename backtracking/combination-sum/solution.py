# sortsort · Combination Sum
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/combination-sum

candidates = [2, 3, 6, 7]
target = 7


def combine(start, remaining, current, results):
    if remaining == 0:
        results.append(current[:])
        return
    for i in range(start, len(candidates)):
        if candidates[i] > remaining:
            continue
        current.append(candidates[i])
        combine(i, remaining - candidates[i], current, results)
        current.pop()


if __name__ == "__main__":
    results = []
    combine(0, target, [], results)
    for combo in results:
        print(combo)
