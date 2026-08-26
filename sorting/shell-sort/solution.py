# sortsort · Shell Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/shell-sort

def shell_sort(arr):
    # Gapped insertion sorts with a gap that halves every pass.
    n = len(arr)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2
    return arr


if __name__ == "__main__":
    data = [12, 34, 54, 2, 3]
    print("array:", data)
    print("sorted:", shell_sort(data))
