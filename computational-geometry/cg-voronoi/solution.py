# sortsort · Voronoi Diagram
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-voronoi

def voronoi_grid(sites, labels, size=20):
    for gy in range(size, -1, -1):
        row = ""
        for gx in range(size + 1):
            best = 0
            bd = float("inf")
            for i, (sx, sy) in enumerate(sites):
                d = (gx - sx) ** 2 + (gy - sy) ** 2
                if d < bd:
                    bd = d
                    best = i
            row += labels[best]
        print(row)


if __name__ == "__main__":
    voronoi_grid([(4, 16), (16, 16), (10, 4)], ["A", "B", "C"])
