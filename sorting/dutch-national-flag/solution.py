# Stepsort · Dutch National Flag
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dutch-national-flag

def dutch_national_flag(arr, pivot_index=None):
    if pivot_index is None:
        pivot_index = len(arr) // 2
    pivot = arr[pivot_index]
    low, mid, high = 0, 0, len(arr) - 1
    while mid <= high:
        if arr[mid] < pivot:
            arr[low], arr[mid] = arr[mid], arr[low]
            low += 1
            mid += 1
        elif arr[mid] == pivot:
            mid += 1
        else:
            arr[mid], arr[high] = arr[high], arr[mid]
            high -= 1
    return low, mid  # boundaries: [0..low) < pivot, [low..mid) == pivot, [mid..n) > pivot

if __name__ == "__main__":
    data = [2, 0, 1, 2, 1, 0]
    lo, hi = dutch_national_flag(data)
    print("partitioned:", data)
