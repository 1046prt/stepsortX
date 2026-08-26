# sortsort · Jarvis March
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-jarvis

def cross(o, a, b):
    return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0])


def dist2(a, b):
    return (a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2


def jarvis_march(points):
    n = len(points)
    start = min(range(n), key=lambda i: points[i][0])
    hull = []
    p = start
    while True:
        hull.append(points[p])
        q = (p + 1) % n
        for i in range(n):
            c = cross(points[p], points[i], points[q])
            if c > 0 or (c == 0 and dist2(points[p], points[i]) > dist2(points[p], points[q])):
                q = i
        p = q
        if p == start:
            break
    return hull


if __name__ == "__main__":
    pts = [(0, 0), (4, 0), (4, 3), (0, 3), (2, 1)]
    print("hull:", jarvis_march(pts))
