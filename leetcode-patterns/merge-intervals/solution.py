# sortsort · Merge Intervals
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-intervals

def merge(intervals):
    # Sort by start, then extend or append each interval in order.
    intervals.sort(key=lambda interval: interval[0])
    merged = []
    for start, end in intervals:
        if merged and merged[-1][1] >= start:
            merged[-1][1] = max(merged[-1][1], end)
        else:
            merged.append([start, end])
    return merged


if __name__ == "__main__":
    print(merge([[1, 3], [2, 6], [8, 10], [15, 18]]))
