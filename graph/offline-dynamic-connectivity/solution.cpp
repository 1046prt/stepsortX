// Stepsort · Offline Dynamic Connectivity
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/offline-dynamic-connectivity

#include <bits/stdc++.h>
using namespace std;

struct RollbackDSU {
    vector<int> p, sz, log;
    RollbackDSU(int n): p(n), sz(n, 1) { iota(p.begin(), p.end(), 0); }
    int find(int x){ while(p[x]!=x) x=p[x]; return x; }
    void unite(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) { log.push_back(-1); return; }
        if (sz[ra] < sz[rb]) swap(ra, rb);
        p[rb] = ra; sz[ra] += sz[rb]; log.push_back(rb);
    }
    void rollback(int mark) {
        while ((int)log.size() > mark) {
            int rb = log.back(); log.pop_back();
            if (rb != -1) { sz[p[rb]] -= sz[rb]; p[rb] = rb; }
        }
    }
};

int main() {
    int n = 5;
    RollbackDSU dsu(n);
    vector<tuple<int,int,int,int>> lifespan =
        {{0,1,0,6},{1,2,0,3},{2,3,2,6},{0,3,1,4},{3,4,4,6}};

    for (int t = 0; t < 6; t++) {
        int mark = dsu.log.size();
        for (auto& [u, v, s, e] : lifespan)
            if (s <= t && t < e) dsu.unite(u, v);
        set<int> roots;
        for (int v = 0; v < n; v++) roots.insert(dsu.find(v));
        cout << "t=" << t << ": " << roots.size() << " components" << endl;
        dsu.rollback(mark);
    }
}
