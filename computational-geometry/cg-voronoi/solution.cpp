// sortsort · Voronoi Diagram
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-voronoi

#include <bits/stdc++.h>
using namespace std;

int main() {
    double sx[3] = {4, 16, 10};
    double sy[3] = {16, 16, 4};
    string labels = "ABC";
    for (int gy = 20; gy >= 0; gy--) {
        for (int gx = 0; gx <= 20; gx++) {
            int best = 0;
            double bd = 1e18;
            for (int i = 0; i < 3; i++) {
                double dx = gx - sx[i], dy = gy - sy[i];
                double d = dx * dx + dy * dy;
                if (d < bd) { bd = d; best = i; }
            }
            cout << labels[best];
        }
        cout << endl;
    }
    return 0;
}
