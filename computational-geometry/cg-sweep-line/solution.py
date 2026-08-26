# Stepsort · Sweep Line Intersections
# Category: Computational Geometry
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-sweep-line

def orientation(a, b, c):
    v = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0])
    return 0 if v == 0 else (1 if v > 0 else 2)


def on_segment(a, b, p):
    return (min(a[0], b[0]) <= p[0] <= max(a[0], b[0]) and
            min(a[1], b[1]) <= p[1] <= max(a[1], b[1]))


def segments_intersect(s1, s2):
    p1, p2 = s1
    p3, p4 = s2
    o1 = orientation(p1, p2, p3)
    o2 = orientation(p1, p2, p4)
    o3 = orientation(p3, p4, p1)
    o4 = orientation(p3, p4, p2)
    if o1 != o2 and o3 != o4:
        return True
    if o1 == 0 and on_segment(p1, p2, p3):
        return True
    if o2 == 0 and on_segment(p1, p2, p4):
        return True
    if o3 == 0 and on_segment(p3, p4, p1):
        return True
    if o4 == 0 and on_segment(p3, p4, p2):
        return True
    return False


def sweep_line_intersections(segments):
    events = []
    for i, s in enumerate(segments):
        a, b = s
        if a[0] > b[0]:
            a, b = b, a
        events.append((a[0], 0, i))
        events.append((b[0], 1, i))
    events.sort(key=lambda e: (e[0], e[1]))
    active = []
    found = set()
    for _, kind, i in events:
        if kind == 0:
            for j in active:
                if segments_intersect(segments[i], segments[j]):
                    found.add((min(i, j), max(i, j)))
            active.append(i)
        else:
            active.remove(i)
    return sorted(found)


if __name__ == "__main__":
    segs = [
        ((0, 0), (4, 4)),
        ((4, 0), (0, 4)),
        ((5, 5), (7, 7)),
        ((0, 5), (5, 0)),
    ]
    print("intersecting pairs:", sweep_line_intersections(segs))
