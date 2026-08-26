# sortsort · Polygon Area (Shoelace)
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-polygon-area

def polygon_area(poly):
    n = len(poly)
    s = 0.0
    for i in range(n):
        x1, y1 = poly[i]
        x2, y2 = poly[(i + 1) % n]
        s += x1 * y2 - x2 * y1
    return abs(s) / 2.0


if __name__ == "__main__":
    poly = [(0, 0), (4, 0), (4, 3), (0, 3)]
    print("area:", polygon_area(poly))
