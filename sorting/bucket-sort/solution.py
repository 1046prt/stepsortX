# sortsort · Bucket Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bucket-sort

def insertion_sort(bucket):
    # Standard insertion sort applied within one bucket.
    for i in range(1, len(bucket)):
        key = bucket[i]
        j = i - 1
        while j >= 0 and bucket[j] > key:
            bucket[j + 1] = bucket[j]
            j -= 1
        bucket[j + 1] = key


def bucket_sort(arr):
    # Scatter values in [0, 1) across n buckets, sort each, concatenate.
    n = len(arr)
    if n == 0:
        return []
    buckets = [[] for _ in range(n)]
    for value in arr:
        index = min(int(value * n), n - 1)
        buckets[index].append(value)
    result = []
    for bucket in buckets:
        insertion_sort(bucket)
        result.extend(bucket)
    return result


if __name__ == "__main__":
    data = [0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51]
    print("array:", data)
    print("sorted:", bucket_sort(data))
