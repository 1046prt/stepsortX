# Stepsort · Quick Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/quick-sort

def partition(arr, low, high):
    # Lomuto scheme: pivot is the last element of the range.
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1


def quick_sort(arr, low=0, high=None):
    if high is None:
        high = len(arr) - 1
    if low < high:
        p = partition(arr, low, high)
        quick_sort(arr, low, p - 1)
        quick_sort(arr, p + 1, high)
    return arr


if __name__ == "__main__":
    data = [10, 7, 8, 9, 1, 5]
    print("array:", data)
    print("sorted:", quick_sort(data))
