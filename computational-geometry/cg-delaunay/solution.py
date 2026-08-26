# sortsort · Delaunay Triangulation
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-delaunay

def orient(a, b, c):
    return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0])


def in_circumcircle(a, b, c, d, eps=1e-7):
    adx, ady = a[0] - d[0], a[1] - d[1]
    bdx, bdy = b[0] - d[0], b[1] - d[1]
    cdx, cdy = c[0] - d[0], c[1] - d[1]
    al = adx * adx + ady * ady
    bl = bdx * bdx + bdy * bdy
    cl = cdx * cdx + cdy * cdy
    det = (adx * (bdy * cl - bl * cdy)
           - ady * (bdx * cl - bl * cdx)
           + al * (bdx * cdy - bdy * cdx))
    return det > eps


def delaunay(points):
    n = len(points)
    pts = list(points) + [(-100.0, -100.0), (200.0, -100.0), (50.0, 200.0)]
    tris = [(n, n + 1, n + 2)]
    for i in range(n):
        bad = [t for t in tris if in_circumcircle(pts[t[0]], pts[t[1]], pts[t[2]], pts[i])]
        counts = {}
        for t in bad:
            for u, v in ((t[0], t[1]), (t[1], t[2]), (t[2], t[0])):
                key = (u, v) if u < v else (v, u)
                counts[key] = counts.get(key, 0) + 1
        bad_set = set(bad)
        tris = [t for t in tris if t not in bad_set]
        for u, v in [e for e, c in counts.items() if c == 1]:
            if orient(pts[u], pts[v], pts[i]) < 0:
                u, v = v, u
            tris.append((u, v, i))
    return [t for t in tris if t[0] < n and t[1] < n and t[2] < n]


if __name__ == "__main__":
    pts = [(2.0, 1.0), (4.0, 6.0), (7.0, 2.0), (1.0, 5.0), (6.0, 7.0)]
    for t in delaunay(pts):
        print("triangle:", t)
