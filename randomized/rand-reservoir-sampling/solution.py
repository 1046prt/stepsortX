# sortsort · Reservoir Sampling
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-reservoir-sampling

import random


def reservoir_sample(stream, k):
    # Keep k items; the i-th item (0-based) replaces a random slot with prob k/i.
    reservoir = []
    for index, item in enumerate(stream):
        if index < k:
            reservoir.append(item)
        else:
            j = random.randint(0, index)
            if j < k:
                reservoir[j] = item
    return reservoir


def number_stream(n):
    # Stand-in for a stream whose length is unknown ahead of time.
    yield from range(1, n + 1)


if __name__ == "__main__":
    random.seed(42)
    trials = [reservoir_sample(number_stream(20), 3) for _ in range(5)]
    for trial in trials:
        print("sample of 3 from stream of 20:", sorted(trial))
