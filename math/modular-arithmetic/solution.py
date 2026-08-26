# sortsort · Modular Arithmetic
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/modular-arithmetic

# Modular arithmetic with a large prime modulus.
MOD = 1000000007


def mod_add(a, b):
    return (a + b) % MOD


def mod_sub(a, b):
    # Python's % always returns a non-negative value for positive modulus.
    return (a - b) % MOD


def mod_mul(a, b):
    return a * b % MOD


def mod_pow(a, b):
    result = 1
    a %= MOD
    while b > 0:
        if b & 1:
            result = result * a % MOD
        a = a * a % MOD
        b >>= 1
    return result


def mod_inverse(a):
    # Fermat's little theorem: valid because MOD is prime.
    return mod_pow(a, MOD - 2)


if __name__ == "__main__":
    print("add:", mod_add(1000000006, 2))          # wraps to 1
    print("sub:", mod_sub(3, 5))                    # wraps to MOD - 2
    print("mul:", mod_mul(123456789, 987654321))
    print("inverse of 2:", mod_inverse(2))
    print("check inverse(7) * 7 mod M:", mod_mul(mod_inverse(7), 7))
