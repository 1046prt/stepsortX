# sortsort · Gnome Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/gnome-sort

def gnome_sort(arr):
    # Move forward when ordered; otherwise swap back and step backward.
    i = 0
    while i < len(arr):
        if i == 0 or arr[i] >= arr[i - 1]:
            i += 1
        else:
            arr[i], arr[i - 1] = arr[i - 1], arr[i]
            i -= 1
    return arr


if __name__ == "__main__":
    data = [34, 2, 10, 9, 7, 8]
    print("array:", data)
    print("sorted:", gnome_sort(data))
