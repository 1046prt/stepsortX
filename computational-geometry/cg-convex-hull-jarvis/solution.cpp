// Stepsort · Jarvis March
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-jarvis

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double cross(Pt o, Pt a, Pt b) {
    return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
}

double dist2(Pt a, Pt b) {
    double dx = a.x - b.x, dy = a.y - b.y;
    return dx * dx + dy * dy;
}

vector<Pt> jarvisMarch(vector<Pt> pts) {
    int n = pts.size(), start = 0;
    for (int i = 1; i < n; i++)
        if (pts[i].x < pts[start].x) start = i;
    vector<Pt> hull;
    int p = start;
    do {
        hull.push_back(pts[p]);
        int q = (p + 1) % n;
        for (int i = 0; i < n; i++) {
            double c = cross(pts[p], pts[i], pts[q]);
            if (c > 0 || (c == 0 && dist2(pts[p], pts[i]) > dist2(pts[p], pts[q])))
                q = i;
        }
        p = q;
    } while (p != start);
    return hull;
}

int main() {
    vector<Pt> pts = {{0, 0}, {4, 0}, {4, 3}, {0, 3}, {2, 1}};
    cout << "hull:";
    for (auto& p : jarvisMarch(pts)) cout << " (" << p.x << "," << p.y << ")";
    cout << endl;
    return 0;
}
