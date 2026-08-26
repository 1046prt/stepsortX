# Stepsort · Cycle Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-sort

def cycle_sort(arr):
    # Each element goes straight to its final slot; counts array writes.
    n = len(arr)
    writes = 0
    for start in range(n - 1):
        item = arr[start]
        pos = start
        for i in range(start + 1, n):
            if arr[i] < item:
                pos += 1
        if pos == start:
            continue
        while item == arr[pos]:
            pos += 1
        arr[pos], item = item, arr[pos]
        writes += 1
        while pos != start:
            pos = start
            for i in range(start + 1, n):
                if arr[i] < item:
                    pos += 1
            while item == arr[pos]:
                pos += 1
            arr[pos], item = item, arr[pos]
            writes += 1
    return writes


if __name__ == "__main__":
    data = [4, 2, 5, 1, 3, 4]
    print("array:", data)
    print("writes:", cycle_sort(data))
    print("sorted:", data)
