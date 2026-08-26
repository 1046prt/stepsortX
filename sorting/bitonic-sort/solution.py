# sortsort · Bitonic Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bitonic-sort

def compare_and_swap(arr, i, j, ascending):
    if (ascending and arr[i] > arr[j]) or (not ascending and arr[i] < arr[j]):
        arr[i], arr[j] = arr[j], arr[i]


def bitonic_merge(arr, low, count, ascending):
    if count <= 1:
        return
    mid = count // 2
    for i in range(low, low + mid):
        compare_and_swap(arr, i, i + mid, ascending)
    bitonic_merge(arr, low, mid, ascending)
    bitonic_merge(arr, low + mid, mid, ascending)


def bitonic_sort(arr, low=0, count=None, ascending=True):
    # Sorting network for power-of-two lengths.
    if count is None:
        count = len(arr)
    if count <= 1:
        return
    mid = count // 2
    bitonic_sort(arr, low, mid, True)
    bitonic_sort(arr, low + mid, mid, False)
    bitonic_merge(arr, low, count, ascending)


if __name__ == "__main__":
    data = [3, 7, 4, 8, 6, 2, 1, 5]
    print("array:", data)
    bitonic_sort(data)
    print("sorted:", data)
