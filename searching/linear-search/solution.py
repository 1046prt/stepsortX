# Stepsort · Linear Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linear-search

def linear_search(arr, target):
    # Scan every element from left to right.
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1


if __name__ == "__main__":
    data = [4, 2, 7, 1, 9, 5]
    print("array:", data)
    print("index of 7:", linear_search(data, 7))
    print("index of 3:", linear_search(data, 3))
