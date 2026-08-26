// sortsort · Centroid Decomposition
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/centroid-decomposition

#include <bits/stdc++.h>
using namespace std;

int main() {
    int n = 12;
    vector<vector<int>> adj(n);
    int edgeList[][2] = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6},
                         {3, 7}, {3, 8}, {4, 9}, {5, 10}, {7, 11}};
    for (auto& e : edgeList) {
        adj[e[0]].push_back(e[1]);
        adj[e[1]].push_back(e[0]);
    }
    vector<bool> alive(n, true);
    vector<int> removal;
    while ((int)removal.size() < n) {
        int root = 0;
        while (!alive[root]) root++;
        vector<int> par(n, -1), stk, preorder;
        vector<bool> visited(n, false);
        visited[root] = true;
        stk.push_back(root);
        while (!stk.empty()) {
            int v = stk.back();
            stk.pop_back();
            preorder.push_back(v);
            for (int u : adj[v]) {
                if (alive[u] && !visited[u]) {
                    visited[u] = true;
                    par[u] = v;
                    stk.push_back(u);
                }
            }
        }
        vector<int> sz(n, 1);
        for (int i = (int)preorder.size() - 1; i >= 0; i--) {
            int v = preorder[i];
            if (par[v] != -1) sz[par[v]] += sz[v];
        }
        int total = sz[root], centroid = -1;
        for (int v : preorder) {
            int worst = total - sz[v];
            for (int u : adj[v])
                if (alive[u] && u != par[v] && visited[u])
                    worst = max(worst, sz[u]);
            if (worst <= total / 2) { centroid = v; break; }
        }
        removal.push_back(centroid);
        alive[centroid] = false;
    }
    cout << "centroid removal order:";
    for (int v : removal) cout << " " << v;
    cout << endl;
}
