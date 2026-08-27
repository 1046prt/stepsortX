// Stepsort · Count-Min Sketch
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-min-sketch

#include <bits/stdc++.h>
using namespace std;

struct CountMinSketch {
    int width, depth;
    vector<vector<int>> table;
    vector<function<int(int)>> hashes;
    CountMinSketch(int w, int d) : width(w), depth(d), table(d, vector<int>(w, 0)) {
        for (int i = 0; i < d; i++)
            hashes.push_back([i, w](int x) { return ((x * (i+1) * 7 + i * 3) % w + w) % w; });
    }
    void update(int item, int count = 1) {
        for (int d = 0; d < depth; d++)
            table[d][hashes[d](item)] += count;
    }
    int query(int item) {
        int mn = INT_MAX;
        for (int d = 0; d < depth; d++)
            mn = min(mn, table[d][hashes[d](item)]);
        return mn;
    }
};

int main() {
    CountMinSketch cms(8, 3);
    for (int item : {3, 1, 4, 1, 5, 9, 2, 6})
        cms.update(item);
    cout << "estimate for 1: " << cms.query(1) << endl;
    cout << "estimate for 9: " << cms.query(9) << endl;
    return 0;
}
