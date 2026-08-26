# Stepsort · Selection Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/selection-sort

def selection_sort(arr):
    # Pick the minimum of the unsorted part and move it to the front.
    n = len(arr)
    for i in range(n - 1):
        min_idx = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr


if __name__ == "__main__":
    data = [64, 25, 12, 22, 11]
    print("array:", data)
    print("sorted:", selection_sort(data))
