# sortsort · Fibonacci Search
# Category: Searching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-search

def fibonacci_search(arr, target):
    # Probe split points given by Fibonacci numbers on a sorted array.
    n = len(arr)
    if n == 0:
        return -1
    fib_m2, fib_m1 = 0, 1  # F(k-2), F(k-1)
    fib = fib_m2 + fib_m1  # F(k)
    while fib < n:
        fib_m2 = fib_m1
        fib_m1 = fib
        fib = fib_m2 + fib_m1
    offset = -1
    while fib > 1:
        i = min(offset + fib_m2, n - 1)
        if arr[i] == target:
            return i
        elif arr[i] < target:
            fib = fib_m1
            fib_m1 = fib_m2
            fib_m2 = fib - fib_m1
            offset = i
        else:
            fib = fib_m2
            fib_m1 = fib_m1 - fib_m2
            fib_m2 = fib - fib_m1
    if offset + 1 < n and arr[offset + 1] == target:
        return offset + 1
    return -1


if __name__ == "__main__":
    data = [10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100]
    print("array:", data)
    print("index of 85:", fibonacci_search(data, 85))
    print("index of 42:", fibonacci_search(data, 42))
