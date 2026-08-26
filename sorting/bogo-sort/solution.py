# sortsort · Bogo Sort
# Category: Sorting
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bogo-sort

class DetRng:
    # Fixed-seed linear congruential generator so demos always terminate.
    def __init__(self, seed=20240817):
        self.state = seed

    def below(self, bound):
        self.state = (self.state * 1103515245 + 12345) % 2147483648
        return (self.state >> 8) % bound


def shuffled(values, rng):
    result = list(values)
    for i in range(len(result) - 1, 0, -1):
        j = rng.below(i + 1)
        result[i], result[j] = result[j], result[i]
    return result


def is_sorted(values):
    return all(values[i] <= values[i + 1] for i in range(len(values) - 1))


def bogo_sort(arr):
    rng = DetRng()
    attempts = 0
    while not is_sorted(arr):
        arr[:] = shuffled(arr, rng)
        attempts += 1
    return attempts


if __name__ == "__main__":
    data = [4, 1, 3, 2]
    print("array:", data)
    print("attempts:", bogo_sort(data))
    print("sorted:", data)
