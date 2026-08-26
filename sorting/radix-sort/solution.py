# Stepsort · Radix Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/radix-sort

def counting_sort_by_digit(arr, exp):
    # Stable counting sort keyed by the digit at place value exp.
    n = len(arr)
    output = [0] * n
    counts = [0] * 10
    for value in arr:
        counts[(value // exp) % 10] += 1
    for digit in range(1, 10):
        counts[digit] += counts[digit - 1]
    for i in range(n - 1, -1, -1):
        digit = (arr[i] // exp) % 10
        counts[digit] -= 1
        output[counts[digit]] = arr[i]
    return output


def radix_sort(arr):
    # LSD passes, one per decimal digit of the maximum value.
    if not arr:
        return arr
    max_value = max(arr)
    exp = 1
    while max_value // exp > 0:
        arr = counting_sort_by_digit(arr, exp)
        exp *= 10
    return arr


if __name__ == "__main__":
    data = [170, 45, 75, 90, 802, 24, 2, 66]
    print("array:", data)
    print("sorted:", radix_sort(data))
