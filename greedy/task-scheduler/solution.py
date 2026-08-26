# sortsort · Task Scheduler
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/task-scheduler

from collections import Counter


def least_interval(tasks, cooldown):
    # idle units are bounded by the most frequent task
    counts = Counter(tasks)
    max_freq = max(counts.values())
    count_max = sum(1 for c in counts.values() if c == max_freq)
    return max(len(tasks), (max_freq - 1) * (cooldown + 1) + count_max)


if __name__ == "__main__":
    print("Minimum units:", least_interval(["A", "A", "A", "B", "B", "B"], 2))
    print("Minimum units:", least_interval(["A", "C", "A", "B", "D", "B"], 1))
