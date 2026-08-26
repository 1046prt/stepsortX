# sortsort · Fast Exponentiation
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/fast-exponentiation

# Binary exponentiation: compute a^b with O(log b) multiplications.
def power(a, b):
    result = 1
    while b > 0:
        if b & 1:
            result *= a
        a *= a
        b >>= 1
    return result


# Modular version keeps every product below m squared.
def pow_mod(a, b, m):
    result = 1
    a %= m
    while b > 0:
        if b & 1:
            result = result * a % m
        a = a * a % m
        b >>= 1
    return result


if __name__ == "__main__":
    print("2^10 =", power(2, 10))
    print("3^13 =", power(3, 13))
    print("5^20 =", power(5, 20))
    print("2^100 mod 1000000007 =", pow_mod(2, 100, 1000000007))
    print("123456789^987654321 mod 1000000007 =",
          pow_mod(123456789, 987654321, 1000000007))
