# sortsort · Counting Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/counting-sort

def counting_sort(arr):
    # Tally occurrences of each value, assuming non-negative integers.
    if not arr:
        return []
    max_value = max(arr)
    counts = [0] * (max_value + 1)
    for value in arr:
        counts[value] += 1
    result = []
    for value in range(max_value + 1):
        result.extend([value] * counts[value])
    return result


if __name__ == "__main__":
    data = [4, 2, 2, 8, 3, 3, 1]
    print("array:", data)
    print("sorted:", counting_sort(data))
