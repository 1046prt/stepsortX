# sortsort · Permutations
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/permutations

items = [1, 2, 3]


def permute(items):
    results = []

    def backtrack(current, used):
        if len(current) == len(items):
            results.append(current[:])
            return
        for i in range(len(items)):
            if not used[i]:
                used[i] = True
                current.append(items[i])
                backtrack(current, used)
                current.pop()
                used[i] = False

    backtrack([], [False] * len(items))
    return results


if __name__ == "__main__":
    for p in permute(items):
        print(p)
