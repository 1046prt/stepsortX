// Stepsort · Line Intersection
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-line-intersection

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double direction(Pt a, Pt b, Pt c) {
    return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
}

bool onSegment(Pt a, Pt b, Pt p) {
    return min(a.x, b.x) <= p.x && p.x <= max(a.x, b.x) &&
           min(a.y, b.y) <= p.y && p.y <= max(a.y, b.y);
}

bool segmentsIntersect(Pt p1, Pt p2, Pt p3, Pt p4) {
    double d1 = direction(p3, p4, p1);
    double d2 = direction(p3, p4, p2);
    double d3 = direction(p1, p2, p3);
    double d4 = direction(p1, p2, p4);
    if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
        ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)))
        return true;
    if (d1 == 0 && onSegment(p3, p4, p1)) return true;
    if (d2 == 0 && onSegment(p3, p4, p2)) return true;
    if (d3 == 0 && onSegment(p1, p2, p3)) return true;
    if (d4 == 0 && onSegment(p1, p2, p4)) return true;
    return false;
}

int main() {
    cout << boolalpha;
    cout << "s1 x s2: " << segmentsIntersect({0, 0}, {4, 4}, {0, 4}, {4, 0}) << endl;
    cout << "s1 x s3: " << segmentsIntersect({0, 0}, {4, 4}, {6, 6}, {8, 8}) << endl;
    return 0;
}
