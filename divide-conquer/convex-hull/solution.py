# sortsort · Convex Hull
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull

def cross(o, a, b):
    # z-component of (a - o) x (b - o)
    return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0])


def convex_hull(points):
    # Andrew monotone chain; returns hull vertices counter-clockwise,
    # collinear points dropped
    pts = sorted(set(points))
    if len(pts) <= 2:
        return pts
    lower = []
    for p in pts:
        while len(lower) >= 2 and cross(lower[-2], lower[-1], p) <= 0:
            lower.pop()
        lower.append(p)
    upper = []
    for p in reversed(pts):
        while len(upper) >= 2 and cross(upper[-2], upper[-1], p) <= 0:
            upper.pop()
        upper.append(p)
    return lower[:-1] + upper[:-1]


if __name__ == "__main__":
    points = [(0, 0), (2, 0), (2, 4), (0, 4), (1, 1), (1, 2)]
    hull = convex_hull(points)
    print("input points:", sorted(points))
    print("hull vertices:")
    for p in hull:
        print(" ", p)
