# Stepsort · Cocktail Shaker Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cocktail-sort

def cocktail_sort(arr):
    # Bubble passes alternate direction, tightening both ends.
    start = 0
    end = len(arr) - 1
    swapped = True
    while swapped:
        swapped = False
        for i in range(start, end):
            if arr[i] > arr[i + 1]:
                arr[i], arr[i + 1] = arr[i + 1], arr[i]
                swapped = True
        if not swapped:
            break
        swapped = False
        end -= 1
        for i in range(end, start, -1):
            if arr[i] < arr[i - 1]:
                arr[i], arr[i - 1] = arr[i - 1], arr[i]
                swapped = True
        start += 1
    return arr


if __name__ == "__main__":
    data = [5, 1, 4, 2, 8, 0, 6]
    print("array:", data)
    print("sorted:", cocktail_sort(data))
