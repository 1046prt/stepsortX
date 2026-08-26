# sortsort · Rotating Calipers
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-rotating-calipers

def cross(o, a, b):
    return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0])


def dist2(a, b):
    return (a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2


def convex_diameter(hull):
    n = len(hull)
    j = 1
    best = 0.0
    for i in range(n):
        ni = (i + 1) % n
        while True:
            nj = (j + 1) % n
            if cross(hull[i], hull[ni], hull[nj]) > cross(hull[i], hull[ni], hull[j]):
                j = nj
            else:
                break
        best = max(best, dist2(hull[i], hull[j]), dist2(hull[ni], hull[j]))
    return best ** 0.5


if __name__ == "__main__":
    hull = [(0, 0), (4, 0), (4, 3), (0, 3)]
    print("diameter:", convex_diameter(hull))
