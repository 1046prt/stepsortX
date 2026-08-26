# Stepsort · Möbius Function
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-mobius-function

def build_mobius(n):
    # smallest-prime-factor sieve, then mu by recurrence
    spf = list(range(n + 1))
    for i in range(2, int(n ** 0.5) + 1):
        if spf[i] == i:
            for j in range(i * i, n + 1, i):
                if spf[j] == j:
                    spf[j] = i
    mu = [0] * (n + 1)
    mu[1] = 1
    # strip the smallest prime p from i = p*rest;
    # if p divides rest then p^2 | i so mu(i) = 0, else mu(i) = -mu(rest)
    for i in range(2, n + 1):
        p = spf[i]
        rest = i // p
        mu[i] = 0 if rest % p == 0 else -mu[rest]
    return mu


if __name__ == "__main__":
    N = 20
    mu = build_mobius(N)
    print("values of mu(n) for n = 1..%d:" % N)
    print("n : " + " ".join("%3d" % v for v in range(1, N + 1)))
    print("mu: " + " ".join("%3d" % mu[v] for v in range(1, N + 1)))
    mertens = sum(mu[1:N + 1])
    print("Mertens M(%d) = %d" % (N, mertens))
