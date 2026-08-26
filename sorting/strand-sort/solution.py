# Stepsort · Strand Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/strand-sort

def merge_lists(head, tail):
    merged = []
    i = j = 0
    while i < len(head) and j < len(tail):
        if head[i] <= tail[j]:
            merged.append(head[i])
            i += 1
        else:
            merged.append(tail[j])
            j += 1
    merged.extend(head[i:])
    merged.extend(tail[j:])
    return merged


def strand_sort(arr):
    # Repeatedly pull the increasing subsequence out of input,
    # then merge that sorted strand into the result.
    result = []
    while arr:
        strand = [arr.pop(0)]
        i = 0
        while i < len(arr):
            if arr[i] >= strand[-1]:
                strand.append(arr.pop(i))
            else:
                i += 1
        result = merge_lists(strand, result)
    return result


if __name__ == "__main__":
    data = [10, 2, 8, 4, 6, 1, 9, 3]
    print("array:", data)
    print("sorted:", strand_sort(data))
