// sortsort · Boruvka's MST
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boruvka-mst

#include <bits/stdc++.h>
using namespace std;

struct DSU {
    vector<int> p;
    DSU(int n): p(n) { iota(p.begin(), p.end(), 0); }
    int find(int x){ while(p[x]!=x){p[x]=p[p[x]];x=p[x];} return x; }
};

int main() {
    int n = 6;
    vector<array<int,3>> e = {{0,1,4},{0,2,2},{1,2,1},{1,3,5},{2,4,10},{3,4,2},{3,5,6},{4,5,3}};
    DSU uf(n);
    int comps = n, total = 0, round_ = 0;

    while (comps > 1) {
        round_++;
        map<int, array<int,3>> cheap;
        for (auto& ed : e) {
            int ru = uf.find(ed[0]), rv = uf.find(ed[1]);
            if (ru == rv) continue;
            auto upd = [&](int r) { if (!cheap.count(r) || ed[2] < cheap[r][2]) cheap[r] = ed; };
            upd(ru); upd(rv);
        }
        for (auto& [r, ed] : cheap) {
            int ru = uf.find(ed[0]), rv = uf.find(ed[1]);
            if (ru == rv) continue;
            uf.p[ru] = rv;
            comps--; total += ed[2];
            cout << "round " << round_ << ": edge " << ed[0] << "-" << ed[1]
                 << " (w=" << ed[2] << ")" << endl;
        }
    }
    cout << "MST weight: " << total << endl;   // 20
}
