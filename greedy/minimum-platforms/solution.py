# sortsort · Minimum Platforms
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/minimum-platforms

def minimum_platforms(arrivals, departures):
    # sweep two sorted timelines with two pointers
    arrivals = sorted(arrivals)
    departures = sorted(departures)
    n = len(arrivals)
    platforms = 0
    max_needed = 0
    i = j = 0
    while i < n:
        if arrivals[i] <= departures[j]:
            platforms += 1
            max_needed = max(max_needed, platforms)
            i += 1
        else:
            platforms -= 1
            j += 1
    return max_needed


if __name__ == "__main__":
    arrivals = [900, 1100, 1235, 1300, 1500]
    departures = [1000, 1200, 1240, 1320, 1800]
    print("Platforms needed:", minimum_platforms(arrivals, departures))
