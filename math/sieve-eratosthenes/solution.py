# sortsort · Sieve of Eratosthenes
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sieve-eratosthenes

# Sieve of Eratosthenes: cross out multiples of each prime up to sqrt(limit).
def sieve_of_eratosthenes(limit):
    if limit < 2:
        return []
    is_prime = [True] * (limit + 1)
    is_prime[0] = is_prime[1] = False
    p = 2
    while p * p <= limit:
        if is_prime[p]:
            for multiple in range(p * p, limit + 1, p):
                is_prime[multiple] = False
        p += 1
    return [i for i in range(2, limit + 1) if is_prime[i]]


if __name__ == "__main__":
    primes = sieve_of_eratosthenes(50)
    print("Primes up to 50:", primes)
    print("Count:", len(primes))
    print("Largest:", primes[-1])
