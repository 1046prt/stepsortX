# sortsort · Graham Scan
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-graham

import math


def cross(o, a, b):
    return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0])


def graham_scan(points):
    pivot = min(points, key=lambda p: (p[1], p[0]))
    rest = sorted(
        [p for p in points if p != pivot],
        key=lambda p: math.atan2(p[1] - pivot[1], p[0] - pivot[0]),
    )
    stack = [pivot]
    for p in rest:
        while len(stack) >= 2 and cross(stack[-2], stack[-1], p) <= 0:
            stack.pop()
        stack.append(p)
    return stack


if __name__ == "__main__":
    pts = [(0, 0), (4, 0), (4, 3), (0, 3), (2, 1)]
    print("hull:", graham_scan(pts))
