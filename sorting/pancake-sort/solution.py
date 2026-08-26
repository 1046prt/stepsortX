# Stepsort · Pancake Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/pancake-sort

def flip(arr, k):
    # Reverse the prefix arr[0..k] in place.
    left, right = 0, k
    while left < right:
        arr[left], arr[right] = arr[right], arr[left]
        left += 1
        right -= 1


def find_max_index(arr, limit):
    best = 0
    for i in range(1, limit):
        if arr[i] > arr[best]:
            best = i
    return best


def pancake_sort(arr):
    # Move the max of the unsorted prefix to the front, then flip it into place.
    for size in range(len(arr), 1, -1):
        max_idx = find_max_index(arr, size)
        if max_idx == size - 1:
            continue
        if max_idx != 0:
            flip(arr, max_idx)
        flip(arr, size - 1)
    return arr


if __name__ == "__main__":
    data = [6, 2, 9, 1, 5, 8]
    print("array:", data)
    print("sorted:", pancake_sort(data))
