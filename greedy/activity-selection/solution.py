# sortsort · Activity Selection
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/activity-selection

def activity_selection(activities):
    # activities: list of (name, start, finish)
    ordered = sorted(activities, key=lambda a: a[2])
    selected = []
    last_finish = 0
    for name, start, finish in ordered:
        if not selected or start >= last_finish:
            selected.append(name)
            last_finish = finish
    return selected


if __name__ == "__main__":
    activities = [
        ("A1", 1, 4),
        ("A2", 3, 5),
        ("A3", 0, 6),
        ("A4", 5, 7),
        ("A5", 3, 9),
        ("A6", 5, 8),
    ]
    chosen = activity_selection(activities)
    print("Selected activities:", chosen)
    print("Maximum count:", len(chosen))
