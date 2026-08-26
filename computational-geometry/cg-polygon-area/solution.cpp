// Stepsort · Polygon Area (Shoelace)
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-polygon-area

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double polygonArea(const vector<Pt>& poly) {
    double s = 0;
    int n = poly.size();
    for (int i = 0; i < n; i++) {
        const Pt& a = poly[i];
        const Pt& b = poly[(i + 1) % n];
        s += a.x * b.y - b.x * a.y;
    }
    return fabs(s) / 2.0;
}

int main() {
    vector<Pt> poly = {{0, 0}, {4, 0}, {4, 3}, {0, 3}};
    cout << "area: " << polygonArea(poly) << endl;
    return 0;
}
