# Stepsort · Insertion Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/insertion-sort

def insertion_sort(arr):
    # Insert each element into its place among the already-sorted prefix.
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr


if __name__ == "__main__":
    data = [12, 31, 25, 8, 32, 17]
    print("array:", data)
    print("sorted:", insertion_sort(data))
