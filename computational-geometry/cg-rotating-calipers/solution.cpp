// sortsort · Rotating Calipers
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-rotating-calipers

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

double convexDiameter(const vector<Pt>& hull) {
    int n = hull.size();
    int j = 1;
    double best = 0;
    for (int i = 0; i < n; i++) {
        int ni = (i + 1) % n;
        while (true) {
            int nj = (j + 1) % n;
            if (cross(hull[i], hull[ni], hull[nj]) > cross(hull[i], hull[ni], hull[j]))
                j = nj;
            else
                break;
        }
        best = max(best, max(dist2(hull[i], hull[j]), dist2(hull[ni], hull[j])));
    }
    return sqrt(best);
}

int main() {
    vector<Pt> hull = {{0, 0}, {4, 0}, {4, 3}, {0, 3}};
    cout << "diameter: " << convexDiameter(hull) << endl;
    return 0;
}
