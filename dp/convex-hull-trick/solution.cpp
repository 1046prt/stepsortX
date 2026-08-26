// sortsort · Convex Hull Trick
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull-trick

#include <bits/stdc++.h>
using namespace std;

struct Line { long long m, b; };

bool bad(const Line& a, const Line& b, const Line& c) {
    return (c.b - a.b) * (a.m - b.m) <= (b.b - a.b) * (a.m - c.m);
}

long long value(const Line& l, long long x) { return l.m * x + l.b; }

int main() {
    vector<Line> lines = {{5, 0}, {3, 4}, {1, 7}, {-1, 12}};
    vector<Line> hull;
    for (const Line& ln : lines) {
        while (hull.size() >= 2 && bad(hull[hull.size() - 2], hull.back(), ln))
            hull.pop_back();
        hull.push_back(ln);
    }
    int ptr = 0;
    for (int x : {0, 2, 5}) {
        while (ptr + 1 < (int)hull.size() &&
               value(hull[ptr + 1], x) <= value(hull[ptr], x))
            ptr++;
        cout << "min f(" << x << ") = " << value(hull[ptr], x) << endl;
    }
}
