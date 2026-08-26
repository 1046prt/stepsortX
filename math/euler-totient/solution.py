# Stepsort · Euler's Totient
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/euler-totient

def phi_factorization(n):
    # phi(n) = n * prod(1 - 1/p) over distinct primes p dividing n
    result = n
    p = 2
    while p * p <= n:
        if n % p == 0:
            while n % p == 0:
                n //= p
            result -= result // p
        p += 1
    if n > 1:
        result -= result // n
    return result


def phi_sieve(limit):
    # computes phi(i) for every i from 0 to limit in O(n log log n)
    phi = list(range(limit + 1))
    for i in range(2, limit + 1):
        if phi[i] == i:  # untouched means no smaller factor exists: prime
            for j in range(i, limit + 1, i):
                phi[j] -= phi[j] // i
    return phi


if __name__ == "__main__":
    for n in [1, 12, 36, 97, 100]:
        print(f"phi({n}) = {phi_factorization(n)}")
    print("sieve 1..20:", phi_sieve(20)[1:])
