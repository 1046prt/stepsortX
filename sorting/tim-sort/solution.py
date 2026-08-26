# Stepsort · Tim Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tim-sort

RUN = 32


def insertion_sort(arr, left, right):
    # Sort the slice arr[left..right] in place.
    for i in range(left + 1, right + 1):
        key = arr[i]
        j = i - 1
        while j >= left and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key


def merge(arr, left, mid, right):
    # Combine adjacent sorted runs arr[left..mid] and arr[mid+1..right].
    left_part = arr[left:mid + 1]
    right_part = arr[mid + 1:right + 1]
    i = j = 0
    k = left
    while i < len(left_part) and j < len(right_part):
        if left_part[i] <= right_part[j]:
            arr[k] = left_part[i]
            i += 1
        else:
            arr[k] = right_part[j]
            j += 1
        k += 1
    while i < len(left_part):
        arr[k] = left_part[i]
        i += 1
        k += 1
    while j < len(right_part):
        arr[k] = right_part[j]
        j += 1
        k += 1


def tim_sort(arr):
    # Insertion-sort fixed-size runs, then merge runs bottom-up.
    n = len(arr)
    for start in range(0, n, RUN):
        insertion_sort(arr, start, min(start + RUN - 1, n - 1))
    size = RUN
    while size < n:
        for left in range(0, n, 2 * size):
            mid = min(left + size - 1, n - 1)
            right = min(left + 2 * size - 1, n - 1)
            if mid < right:
                merge(arr, left, mid, right)
        size *= 2
    return arr


if __name__ == "__main__":
    data = [3, 15, 8, 90, 42, 7, 61, 27, 4, 88, 16, 55]
    print("array:", data)
    print("sorted:", tim_sort(data))
