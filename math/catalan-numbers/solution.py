# sortsort · Catalan Numbers
# Category: Math & Number Theory
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/catalan-numbers

# Catalan numbers via DP: c[0] = 1, c[i] = sum of c[j] * c[i-1-j].
def catalan_dp(count):
    cat = [0] * count
    cat[0] = 1
    for i in range(1, count):
        for j in range(i):
            cat[i] += cat[j] * cat[i - 1 - j]
    return cat


def binomial(n, k):
    # Exact integer running product C(n, 0), C(n, 1), ...
    result = 1
    for i in range(k):
        result = result * (n - i) // (i + 1)
    return result


def catalan_closed_form(n):
    # nth Catalan number = C(2n, n) / (n + 1)
    return binomial(2 * n, n) // (n + 1)


if __name__ == "__main__":
    cat = catalan_dp(10)
    print(cat)
    ok = all(catalan_closed_form(i) == cat[i] for i in range(10))
    print("closed form matches:", ok)
