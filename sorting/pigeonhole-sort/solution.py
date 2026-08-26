# Stepsort · Pigeonhole Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pigeonhole-sort

def pigeonhole_sort(arr):
    # Scatter counts into holes over [min, max], then read them back in order.
    if len(arr) == 0:
        return arr
    lo, hi = min(arr), max(arr)
    holes = [0] * (hi - lo + 1)
    for value in arr:
        holes[value - lo] += 1
    i = 0
    for offset in range(len(holes)):
        while holes[offset] > 0:
            arr[i] = offset + lo
            i += 1
            holes[offset] -= 1
    return arr


if __name__ == "__main__":
    data = [9, 3, 7, 1, 8, 3, 5]
    print("array:", data)
    print("sorted:", pigeonhole_sort(data))
