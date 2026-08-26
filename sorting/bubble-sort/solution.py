# Stepsort · Bubble Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bubble-sort

def bubble_sort(arr):
    # Repeatedly swap adjacent out-of-order pairs; stop early if a pass is clean.
    n = len(arr)
    for i in range(n - 1):
        swapped = False
        for j in range(n - 1 - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                swapped = True
        if not swapped:
            break
    return arr


if __name__ == "__main__":
    data = [5, 1, 4, 2, 8]
    print("array:", data)
    print("sorted:", bubble_sort(data))
