// Stepsort · Point in Polygon
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-point-in-polygon

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

bool pointInPolygon(Pt p, const vector<Pt>& poly) {
    bool inside = false;
    int n = poly.size();
    for (int i = 0, j = n - 1; i < n; j = i++) {
        bool yi = poly[i].y > p.y, yj = poly[j].y > p.y;
        if (yi != yj) {
            double xc = (poly[j].x - poly[i].x) * (p.y - poly[i].y) /
                        (poly[j].y - poly[i].y) + poly[i].x;
            if (p.x < xc) inside = !inside;
        }
    }
    return inside;
}

int main() {
    vector<Pt> square = {{0, 0}, {4, 0}, {4, 4}, {0, 4}};
    cout << boolalpha;
    cout << "(2,2) inside: " << pointInPolygon({2, 2}, square) << endl;
    cout << "(5,2) inside: " << pointInPolygon({5, 2}, square) << endl;
    return 0;
}
