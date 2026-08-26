// sortsort · Closest Pair (D&C)
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-closest-pair

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double dist2(Pt a, Pt b) {
    double dx = a.x - b.x, dy = a.y - b.y;
    return dx * dx + dy * dy;
}

double closestRec(vector<Pt>& px, int lo, int hi) {
    if (hi - lo <= 3) {
        double best = numeric_limits<double>::max();
        for (int i = lo; i < hi; i++)
            for (int j = i + 1; j < hi; j++)
                best = min(best, dist2(px[i], px[j]));
        return best;
    }
    int mid = (lo + hi) / 2;
    double d = min(closestRec(px, lo, mid), closestRec(px, mid, hi));
    double midx = px[mid].x;
    vector<Pt> strip;
    for (int i = lo; i < hi; i++)
        if ((px[i].x - midx) * (px[i].x - midx) < d) strip.push_back(px[i]);
    sort(strip.begin(), strip.end(),
         [](const Pt& a, const Pt& b) { return a.y < b.y; });
    for (int i = 0; i < (int)strip.size(); i++)
        for (int j = i + 1; j < (int)strip.size(); j++) {
            double dy = strip[j].y - strip[i].y;
            if (dy * dy >= d) break;
            d = min(d, dist2(strip[i], strip[j]));
        }
    return d;
}

int main() {
    vector<Pt> pts = {{0, 0}, {5, 4}, {3, 1}, {2, 6}, {8, 3}, {7, 7}};
    sort(pts.begin(), pts.end(), [](const Pt& a, const Pt& b) { return a.x < b.x; });
    cout << fixed << setprecision(4);
    cout << "closest distance: " << sqrt(closestRec(pts, 0, (int)pts.size())) << endl;
    return 0;
}
