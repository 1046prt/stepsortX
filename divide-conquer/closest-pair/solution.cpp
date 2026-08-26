// sortsort · Closest Pair of Points
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/closest-pair

#include <bits/stdc++.h>
using namespace std;

struct Point {
    double x, y;
};

double distSq(const Point& a, const Point& b) {
    double dx = a.x - b.x, dy = a.y - b.y;
    return dx * dx + dy * dy;
}

double bruteForce(const vector<Point>& pts, int lo, int hi) {
    double best = numeric_limits<double>::max();
    for (int i = lo; i < hi; i++)
        for (int j = i + 1; j < hi; j++)
            best = min(best, distSq(pts[i], pts[j]));
    return best;
}

// points in [lo, hi) must be sorted by x
double closestPair(const vector<Point>& px, int lo, int hi) {
    if (hi - lo <= 3) return bruteForce(px, lo, hi);
    int mid = (lo + hi) / 2;
    double midX = px[mid].x;
    double best = min(closestPair(px, lo, mid), closestPair(px, mid, hi));
    vector<Point> strip;
    for (int i = lo; i < hi; i++)
        if ((px[i].x - midX) * (px[i].x - midX) < best)
            strip.push_back(px[i]);
    sort(strip.begin(), strip.end(),
         [](const Point& a, const Point& b) { return a.y < b.y; });
    for (size_t i = 0; i < strip.size(); i++)
        for (size_t j = i + 1; j < strip.size(); j++) {
            double dy = strip[j].y - strip[i].y;
            if (dy * dy >= best) break;
            best = min(best, distSq(strip[i], strip[j]));
        }
    return best;
}

int main() {
    vector<Point> points = {{2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}};
    sort(points.begin(), points.end(),
         [](const Point& a, const Point& b) { return a.x < b.x; });
    cout << fixed << setprecision(6);
    cout << "minimum distance: " << sqrt(closestPair(points, 0, (int)points.size())) << endl;
    return 0;
}
