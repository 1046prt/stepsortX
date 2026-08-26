// sortsort · Bridges & Articulation Points
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bridges-articulation

#include <bits/stdc++.h>
using namespace std;

void dfsBridge(int u, int parent, const vector<vector<int>>& adj,
               vector<int>& disc, vector<int>& low, int& timer,
               vector<pair<int,int>>& bridges, set<int>& articulation) {
    disc[u] = low[u] = timer++;
    int children = 0;
    for (int v : adj[u]) {
        if (v == parent) continue;
        if (disc[v] == -1) {
            children++;
            dfsBridge(v, u, adj, disc, low, timer, bridges, articulation);
            low[u] = min(low[u], low[v]);
            if (low[v] > disc[u]) bridges.push_back({u, v});  // no way back
            if (parent != -1 && low[v] >= disc[u])
                articulation.insert(u);  // subtree cannot bypass u
        } else {
            low[u] = min(low[u], disc[v]);  // back edge
        }
    }
    if (parent == -1 && children > 1)
        articulation.insert(u);  // root with separated subtrees
}

int main() {
    int V = 7;
    vector<vector<int>> adj = {
        {1, 2},
        {0, 2},
        {0, 1, 3},
        {2, 4},
        {3, 5, 6},
        {4},
        {4},
    };

    vector<int> disc(V, -1), low(V, 0);
    vector<pair<int,int>> bridges;
    set<int> articulation;
    int timer = 0;
    dfsBridge(0, -1, adj, disc, low, timer, bridges, articulation);

    cout << "Bridges:" << endl;
    for (auto& e : bridges)
        cout << "  " << e.first << " - " << e.second << endl;
    cout << "Articulation points:";
    for (int v : articulation) cout << " " << v;
    cout << endl;
    return 0;
}
