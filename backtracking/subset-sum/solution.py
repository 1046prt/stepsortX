# Stepsort · Subset Sum
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-sum

numbers = [3, 34, 4, 12, 5, 2]
target = 9


def find_subset(i, remaining, current):
    if remaining == 0:
        return current[:]
    if i >= len(numbers) or remaining < 0:
        return None
    # try including numbers[i]
    current.append(numbers[i])
    taken = find_subset(i + 1, remaining - numbers[i], current)
    if taken is not None:
        return taken
    current.pop()
    # try excluding numbers[i]
    return find_subset(i + 1, remaining, current)


if __name__ == "__main__":
    subset = find_subset(0, target, [])
    if subset is not None:
        print("Subset:", subset)
        print("Sum:", sum(subset))
    else:
        print("No subset sums to", target)
