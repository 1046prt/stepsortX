# Stepsort · Comb Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/comb-sort

def comb_sort(arr):
    # Compare items gap apart, shrinking the gap by a factor of 1.3.
    n = len(arr)
    gap = n
    swapped = True
    while gap > 1 or swapped:
        gap = max(1, int(gap / 1.3))
        swapped = False
        for i in range(n - gap):
            if arr[i] > arr[i + gap]:
                arr[i], arr[i + gap] = arr[i + gap], arr[i]
                swapped = True
    return arr


if __name__ == "__main__":
    data = [8, 4, 1, 56, 3, 44, 20]
    print("array:", data)
    print("sorted:", comb_sort(data))
