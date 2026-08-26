# sortsort · Las Vegas Sort
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-las-vegas-sort

import random


def is_sorted(arr):
    return all(arr[i] <= arr[i + 1] for i in range(len(arr) - 1))


def fisher_yates(arr):
    # In-place unbiased shuffle of the caller's list.
    for i in range(len(arr) - 1, 0, -1):
        j = random.randint(0, i)
        arr[i], arr[j] = arr[j], arr[i]


def las_vegas_sort(data):
    # Shuffle and verify until sorted: always correct, random time only.
    result = list(data)
    attempts = 0
    while not is_sorted(result):
        fisher_yates(result)
        attempts += 1
    return result, attempts


if __name__ == "__main__":
    random.seed(42)
    data = [5, 2, 9, 1, 7]
    sorted_data, tries = las_vegas_sort(data)
    print("input:", data)
    print("sorted:", sorted_data)
    print("attempts needed:", tries)
