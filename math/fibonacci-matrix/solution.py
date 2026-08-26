# Stepsort · Fibonacci (Matrix Exp)
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fibonacci-matrix

def mat_mult(a, b):
    top_left = a[0][0] * b[0][0] + a[0][1] * b[1][0]
    top_right = a[0][0] * b[0][1] + a[0][1] * b[1][1]
    bottom_left = a[1][0] * b[0][0] + a[1][1] * b[1][0]
    bottom_right = a[1][0] * b[0][1] + a[1][1] * b[1][1]
    return [[top_left, top_right], [bottom_left, bottom_right]]


def mat_power(m, p):
    result = [[1, 0], [0, 1]]
    while p > 0:
        if p & 1:
            result = mat_mult(result, m)
        m = mat_mult(m, m)
        p >>= 1
    return result


def fib(n):
    # F(n) is an off-diagonal entry of [[1, 1], [1, 0]]^n; F(0) = 0
    if n == 0:
        return 0
    return mat_power([[1, 1], [1, 0]], n)[0][1]


if __name__ == "__main__":
    print("F(0..10):", [fib(i) for i in range(11)])
    print("F(50) =", fib(50))
    print("F(90) =", fib(90))
