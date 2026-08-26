// Stepsort · Delaunay Triangulation
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-delaunay

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double orient(const Pt& a, const Pt& b, const Pt& c) {
    return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
}

bool inCircumcircle(const Pt& a, const Pt& b, const Pt& c, const Pt& d) {
    double adx = a.x - d.x, ady = a.y - d.y;
    double bdx = b.x - d.x, bdy = b.y - d.y;
    double cdx = c.x - d.x, cdy = c.y - d.y;
    double al = adx * adx + ady * ady;
    double bl = bdx * bdx + bdy * bdy;
    double cl = cdx * cdx + cdy * cdy;
    double det = adx * (bdy * cl - bl * cdy)
               - ady * (bdx * cl - bl * cdx)
               + al * (bdx * cdy - bdy * cdx);
    return det > 1e-7;
}

vector<array<int, 3>> delaunay(vector<Pt> pts) {
    int n = pts.size();
    pts.push_back({-100, -100});
    pts.push_back({200, -100});
    pts.push_back({50, 200});
    vector<array<int, 3>> tris = {{n, n + 1, n + 2}};
    for (int i = 0; i < n; i++) {
        map<pair<int, int>, int> cnt;
        vector<array<int, 3>> kept;
        for (auto& t : tris) {
            if (inCircumcircle(pts[t[0]], pts[t[1]], pts[t[2]], pts[i])) {
                int e[3][2] = {{t[0], t[1]}, {t[1], t[2]}, {t[2], t[0]}};
                for (auto& ed : e)
                    cnt[{min(ed[0], ed[1]), max(ed[0], ed[1])}]++;
            } else {
                kept.push_back(t);
            }
        }
        tris = kept;
        for (auto& kv : cnt) {
            if (kv.second != 1) continue;
            int u = kv.first.first, v = kv.first.second;
            if (orient(pts[u], pts[v], pts[i]) < 0) swap(u, v);
            tris.push_back({u, v, i});
        }
    }
    vector<array<int, 3>> out;
    for (auto& t : tris)
        if (t[0] < n && t[1] < n && t[2] < n) out.push_back(t);
    return out;
}

int main() {
    vector<Pt> pts = {{2, 1}, {4, 6}, {7, 2}, {1, 5}, {6, 7}};
    for (auto& t : delaunay(pts))
        cout << "triangle: " << t[0] << " " << t[1] << " " << t[2] << endl;
    return 0;
}
