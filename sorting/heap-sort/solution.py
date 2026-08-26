# Stepsort · Heap Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-sort

def sift_down(arr, root, size):
    # Push arr[root] down until the subtree rooted there is a max-heap.
    while True:
        largest = root
        left = 2 * root + 1
        right = 2 * root + 2
        if left < size and arr[left] > arr[largest]:
            largest = left
        if right < size and arr[right] > arr[largest]:
            largest = right
        if largest == root:
            return
        arr[root], arr[largest] = arr[largest], arr[root]
        root = largest


def heap_sort(arr):
    n = len(arr)
    # Build a max-heap, then repeatedly move the max to the end.
    for i in range(n // 2 - 1, -1, -1):
        sift_down(arr, i, n)
    for end in range(n - 1, 0, -1):
        arr[0], arr[end] = arr[end], arr[0]
        sift_down(arr, 0, end)
    return arr


if __name__ == "__main__":
    data = [4, 10, 3, 5, 1]
    print("array:", data)
    print("sorted:", heap_sort(data))
