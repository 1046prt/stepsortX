# sortsort · Fisher-Yates Shuffle
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-shuffle

import random


def fisher_yates_shuffle(arr):
    # In-place, unbiased: each of the n! orders equally likely.
    for i in range(len(arr) - 1, 0, -1):
        j = random.randint(0, i)  # inclusive on both ends
        arr[i], arr[j] = arr[j], arr[i]


def show(label, values):
    print(label, values)


if __name__ == "__main__":
    original = [1, 2, 3, 4, 5, 6, 7, 8]
    show("original:", original)

    random.seed(1234)
    first_run = list(original)
    fisher_yates_shuffle(first_run)
    show("shuffle with seed 1234:", first_run)

    random.seed(9876)
    second_run = list(original)
    fisher_yates_shuffle(second_run)
    show("shuffle with seed 9876:", second_run)

    counts = {value: 0 for value in original[:2]}
    for _ in range(10000):
        trial = list(original)
        random.shuffle(trial)
        counts[trial[0]] += 1
    print("first-slot frequencies (uniform ~1250 each):", counts)
