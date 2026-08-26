// Stepsort · Graham Scan
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-graham

#include <bits/stdc++.h>
using namespace std;

struct Pt { double x, y; };

double cross(Pt o, Pt a, Pt b) {
    return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
}

vector<Pt> grahamScan(vector<Pt> pts) {
    int idx = 0, n = pts.size();
    for (int i = 1; i < n; i++)
        if (pts[i].y < pts[idx].y || (pts[i].y == pts[idx].y && pts[i].x < pts[idx].x))
            idx = i;
    swap(pts[0], pts[idx]);
    Pt pivot = pts[0];
    sort(pts.begin() + 1, pts.end(), [&](const Pt& a, const Pt& b) {
        return atan2(a.y - pivot.y, a.x - pivot.x) < atan2(b.y - pivot.y, b.x - pivot.x);
    });
    vector<Pt> st;
    st.push_back(pivot);
    for (int i = 1; i < n; i++) {
        while (st.size() >= 2 && cross(st[st.size() - 2], st.back(), pts[i]) <= 0)
            st.pop_back();
        st.push_back(pts[i]);
    }
    return st;
}

int main() {
    vector<Pt> pts = {{0, 0}, {4, 0}, {4, 3}, {0, 3}, {2, 1}};
    cout << "hull:";
    for (auto& p : grahamScan(pts)) cout << " (" << p.x << "," << p.y << ")";
    cout << endl;
    return 0;
}
