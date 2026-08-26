# sortsort · Fibonacci
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-dp

def fib_memo(n, memo):
    # Top-down: recursion plus a cache for overlapping subproblems
    if n <= 1:
        return n
    if memo[n] is not None:
        return memo[n]
    memo[n] = fib_memo(n - 1, memo) + fib_memo(n - 2, memo)
    return memo[n]


def fib_tab(n):
    # Bottom-up: fill the table iteratively
    if n <= 1:
        return n
    table = [0] * (n + 1)
    table[1] = 1
    for i in range(2, n + 1):
        table[i] = table[i - 1] + table[i - 2]
    return table[n]


if __name__ == "__main__":
    n = 10
    memo = [None] * (n + 1)
    print(f"F({n}) top-down memoized: {fib_memo(n, memo)}")
    print(f"F({n}) bottom-up tabulated: {fib_tab(n)}")
