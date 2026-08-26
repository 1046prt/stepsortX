# Stepsort · Interval Scheduling
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/interval-scheduling

def interval_scheduling(intervals):
    # earliest finish time first maximizes non-overlapping intervals
    ordered = sorted(intervals, key=lambda iv: iv[1])
    chosen = []
    last_end = float("-inf")
    for start, end in ordered:
        if not chosen or start >= last_end:
            chosen.append((start, end))
            last_end = end
    return chosen


if __name__ == "__main__":
    intervals = [(1, 3), (2, 4), (3, 5), (0, 7), (5, 8), (6, 9)]
    chosen = interval_scheduling(intervals)
    print("Chosen intervals:", chosen)
    print("Maximum intervals:", len(chosen))
