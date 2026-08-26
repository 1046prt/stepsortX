# Stepsort · Convex Hull Trick
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull-trick

lines = [{"m": 5, "b": 0}, {"m": 3, "b": 4},
         {"m": 1, "b": 7}, {"m": -1, "b": 12}]
hull = []


def bad(a, b, c):
    # line b is unnecessary: intersect(a, c) at or before intersect(a, b)
    return (c["b"] - a["b"]) * (a["m"] - b["m"]) <= (b["b"] - a["b"]) * (a["m"] - c["m"])


def value(line, x):
    return line["m"] * x + line["b"]


for line in lines:
    while len(hull) >= 2 and bad(hull[-2], hull[-1], line):
        hull.pop()
    hull.append(line)

ptr = 0
for x in (0, 2, 5):
    while ptr + 1 < len(hull) and value(hull[ptr + 1], x) <= value(hull[ptr], x):
        ptr += 1
    print("min f(" + str(x) + ") =", value(hull[ptr], x))
