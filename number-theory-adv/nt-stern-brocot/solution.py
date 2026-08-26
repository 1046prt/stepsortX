# Stepsort · Stern-Brocot Tree
# Category: Number Theory (Adv)
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-stern-brocot

from math import gcd


def stern_brocot_path(num, den):
    # L/R path from the root 1/1 down to num/den (positive fraction)
    g = gcd(num, den)
    num //= g
    den //= g
    la, lb, ra, rb = 0, 1, 1, 0       # bounds are 0/1 and 1/0
    path = []
    # compare with the mediant using cross products, no floats needed
    while num * (lb + rb) != den * (la + ra):
        if num * (lb + rb) > den * (la + ra):
            path.append("R")
            la += ra
            lb += rb
        else:
            path.append("L")
            ra += la
            rb += lb
    return "".join(path)


if __name__ == "__main__":
    fractions = ((1, 1), (5, 7), (7, 5), (3, 8), (13, 4))
    for a, b in fractions:
        path = stern_brocot_path(a, b)
        shown = path if path else "(already at root 1/1)"
        print("%d/%d -> %s" % (a, b, shown))
