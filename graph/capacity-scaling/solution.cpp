// sortsort · Capacity Scaling Max Flow
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/capacity-scaling

#include <bits/stdc++.h>
using namespace std;

int n = 6;
vector<vector<long long>> cap, flw;

long long dfs(int u, int sink, long long limit, vector<bool>& vis) {
    if (u == sink) return limit;
    vis.insert(u);
    for (int v = 0; v < n; v++) {
        if (vis.count(v)) continue;
        long long residual = cap[u][v] - flw[u][v];
        if (residual >= limit) {
            long long pushed = dfs(v, sink, min(limit, residual), vis);
            if (pushed > 0) {
                flw[u][v] += pushed;
                flw[v][u] -= pushed;
                return pushed;
            }
        }
    }
    return 0;
}

int main() {
    cap.assign(n, vector<long long>(n, 0));
    flw.assign(n, vector<long long>(n, 0));
    vector<tuple<int,int,int>> caps =
        {{0,1,16},{0,2,13},{1,3,12},{2,1,4},{3,2,9},{2,4,14},{4,3,7},{3,5,20},{4,5,4}};
    for (auto& [a, b, c] : caps) cap[a][b] = c;

    int maxCap = 16, delta = 1, total = 0;
    while (delta * 2 <= maxCap) delta *= 2;

    while (delta >= 1) {
        while (true) {
            vector<bool> vis;
            long long pushed = dfs(0, 5, delta, vis);
            if (pushed == 0) break;
            total += pushed;
            cout << "delta=" << delta << ": +" << pushed << endl;
        }
        delta /= 2;
    }
    cout << "max flow: " << total << endl;   // 23
}
