# Stepsort · Frequency Count
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-frequency

def count_frequency(data):
    counts = {}
    for x in data:
        counts[x] = counts.get(x, 0) + 1
    return counts


if __name__ == "__main__":
    data = [4, 2, 7, 4, 8, 2, 4, 9, 7, 4]
    counts = count_frequency(data)
    for key in sorted(counts):
        print(key, "occurs", counts[key], "time(s)")
