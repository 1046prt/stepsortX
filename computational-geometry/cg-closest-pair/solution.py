# sortsort · Closest Pair (D&C)
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-closest-pair

from math import inf, sqrt


def dist2(a, b):
    return (a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2


def closest_pair(points):
    px = sorted(points)

    def rec(lo, hi):
        if hi - lo <= 3:
            best = inf
            for i in range(lo, hi):
                for j in range(i + 1, hi):
                    best = min(best, dist2(px[i], px[j]))
            return best
        mid = (lo + hi) // 2
        d = min(rec(lo, mid), rec(mid, hi))
        midx = px[mid][0]
        strip = [p for p in px[lo:hi] if (p[0] - midx) ** 2 < d]
        strip.sort(key=lambda p: p[1])
        for i in range(len(strip)):
            for j in range(i + 1, len(strip)):
                dy = strip[j][1] - strip[i][1]
                if dy * dy >= d:
                    break
                d = min(d, dist2(strip[i], strip[j]))
        return d

    return rec(0, len(px))


if __name__ == "__main__":
    pts = [(0, 0), (5, 4), (3, 1), (2, 6), (8, 3), (7, 7)]
    print("closest distance:", round(sqrt(closest_pair(pts)), 4))
