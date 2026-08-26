// Stepsort · Convex Hull
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull

#include <bits/stdc++.h>
using namespace std;

struct Point {
    long long x, y;
};

long long cross(const Point& o, const Point& a, const Point& b) {
    // z-component of (a - o) x (b - o)
    return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
}

// Andrew monotone chain; returns hull vertices counter-clockwise,
// collinear points dropped
vector<Point> convexHull(vector<Point> pts) {
    sort(pts.begin(), pts.end(), [](const Point& a, const Point& b) {
        return a.x < b.x || (a.x == b.x && a.y < b.y);
    });
    pts.erase(unique(pts.begin(), pts.end(), [](const Point& a, const Point& b) {
                  return a.x == b.x && a.y == b.y;
              }), pts.end());
    int n = (int)pts.size();
    if (n <= 2) return pts;
    vector<Point> lower, upper;
    for (const Point& p : pts) {
        while (lower.size() >= 2 && cross(lower[lower.size() - 2], lower.back(), p) <= 0)
            lower.pop_back();
        lower.push_back(p);
    }
    for (int i = n - 1; i >= 0; i--) {
        const Point& p = pts[i];
        while (upper.size() >= 2 && cross(upper[upper.size() - 2], upper.back(), p) <= 0)
            upper.pop_back();
        upper.push_back(p);
    }
    lower.pop_back();
    upper.pop_back();
    lower.insert(lower.end(), upper.begin(), upper.end());
    return lower;
}

int main() {
    vector<Point> points = {{0, 0}, {2, 0}, {2, 4}, {0, 4}, {1, 1}, {1, 2}};
    vector<Point> hull = convexHull(points);
    cout << "hull vertices:" << endl;
    for (const Point& p : hull)
        cout << " (" << p.x << ", " << p.y << ")" << endl;
    return 0;
}
