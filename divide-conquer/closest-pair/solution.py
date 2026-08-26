# Stepsort · Closest Pair of Points
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/closest-pair

import math


def dist_sq(p, q):
    dx = p[0] - q[0]
    dy = p[1] - q[1]
    return dx * dx + dy * dy


def brute_force(pts):
    best = float("inf")
    for i in range(len(pts)):
        for j in range(i + 1, len(pts)):
            best = min(best, dist_sq(pts[i], pts[j]))
    return best


def closest_pair(px):
    # px must already be sorted by x coordinate
    n = len(px)
    if n <= 3:
        return brute_force(px)
    mid = n // 2
    mid_x = px[mid][0]
    best = min(closest_pair(px[:mid]), closest_pair(px[mid:]))
    strip = [p for p in px if (p[0] - mid_x) ** 2 < best]
    strip.sort(key=lambda pt: pt[1])
    for i in range(len(strip)):
        for j in range(i + 1, len(strip)):
            dy = strip[j][1] - strip[i][1]
            if dy * dy >= best:
                break
            best = min(best, dist_sq(strip[i], strip[j]))
    return best


if __name__ == "__main__":
    points = [(2, 3), (12, 30), (40, 50), (5, 1), (12, 10), (3, 4)]
    points.sort()
    print("points:", points)
    print("minimum distance:", round(math.sqrt(closest_pair(points)), 6))
