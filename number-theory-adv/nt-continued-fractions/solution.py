# sortsort · Continued Fractions
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-continued-fractions

def continued_fraction(p, q):
    # expand p/q into [a0; a1, a2, ...] with p/q = a0 + 1/(a1 + 1/(a2 + ...))
    if q < 0:
        p, q = -p, -q
    terms = []
    while q != 0:
        a = p // q                  # floor division keeps every tail positive
        terms.append(a)
        p, q = q, p - a * q
    return terms


if __name__ == "__main__":
    samples = [(43, 19), (649, 200), (5, 3), (13, 8), (7, 1), (-43, 19)]
    for p, q in samples:
        print("%d/%d ->" % (p, q), continued_fraction(p, q))
