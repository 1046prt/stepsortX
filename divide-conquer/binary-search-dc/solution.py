# sortsort · Binary Search (D&C)
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-search-dc

def binary_search(arr, target, low=0, high=None):
    # recursive divide-and-conquer search over a sorted array
    if high is None:
        high = len(arr) - 1
    if low > high:
        return -1
    mid = low + (high - low) // 2
    if arr[mid] == target:
        return mid
    if arr[mid] < target:
        return binary_search(arr, target, mid + 1, high)
    return binary_search(arr, target, low, mid - 1)


if __name__ == "__main__":
    data = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
    print("sorted data:", data)
    for target in (23, 2, 91, 40):
        index = binary_search(data, target)
        if index == -1:
            print(target, "not found")
        else:
            print(target, "found at index", index)
