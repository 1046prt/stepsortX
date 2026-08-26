// Stepsort · Sweep Line Intersections
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-sweep-line

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };
struct Seg { Pt a, b; };

int orientation(Pt a, Pt b, Pt c) {
    double v = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    return v == 0 ? 0 : (v > 0 ? 1 : 2);
}

bool onSegment(Pt a, Pt b, Pt p) {
    return min(a.x, b.x) <= p.x && p.x <= max(a.x, b.x) &&
           min(a.y, b.y) <= p.y && p.y <= max(a.y, b.y);
}

bool segmentsIntersect(const Seg& s1, const Seg& s2) {
    Pt p1 = s1.a, p2 = s1.b, p3 = s2.a, p4 = s2.b;
    int o1 = orientation(p1, p2, p3);
    int o2 = orientation(p1, p2, p4);
    int o3 = orientation(p3, p4, p1);
    int o4 = orientation(p3, p4, p2);
    if (o1 != o2 && o3 != o4) return true;
    if (o1 == 0 && onSegment(p1, p2, p3)) return true;
    if (o2 == 0 && onSegment(p1, p2, p4)) return true;
    if (o3 == 0 && onSegment(p3, p4, p1)) return true;
    if (o4 == 0 && onSegment(p3, p4, p2)) return true;
    return false;
}

vector<pair<int, int>> sweepLineIntersections(vector<Seg> segs) {
    vector<tuple<double, int, int>> events;
    for (int i = 0; i < (int)segs.size(); i++) {
        if (segs[i].a.x > segs[i].b.x) swap(segs[i].a, segs[i].b);
        events.push_back({segs[i].a.x, 0, i});
        events.push_back({segs[i].b.x, 1, i});
    }
    sort(events.begin(), events.end());
    vector<int> active;
    set<pair<int, int>> found;
    for (auto& [x, kind, i] : events) {
        if (kind == 0) {
            for (int j : active)
                if (segmentsIntersect(segs[i], segs[j]))
                    found.insert({min(i, j), max(i, j)});
            active.push_back(i);
        } else {
            active.erase(find(active.begin(), active.end(), i));
        }
    }
    return vector<pair<int, int>>(found.begin(), found.end());
}

int main() {
    vector<Seg> segs = {
        {{0, 0}, {4, 4}},
        {{4, 0}, {0, 4}},
        {{5, 5}, {7, 7}},
        {{0, 5}, {5, 0}},
    };
    cout << "intersecting pairs:";
    for (auto& [i, j] : sweepLineIntersections(segs)) cout << " (" << i << "," << j << ")";
    cout << endl;
    return 0;
}
