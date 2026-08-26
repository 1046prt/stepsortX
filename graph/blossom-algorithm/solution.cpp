// Stepsort · Blossom Algorithm (Edmonds)
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/blossom-algorithm

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj(7);
vector<int> match_(7, -1);

int main() {
    vector<pair<int,int>> e = {{0,1},{1,2},{2,3},{3,4},{4,0},{4,5},{5,6}};
    for (auto& [a, b] : e) { adj[a].push_back(b); adj[b].push_back(a); }

    // greedy seeding
    for (int u = 0; u < 7; u++) {
        if (match_[u] != -1) continue;
        for (int v : adj[u]) {
            if (match_[v] == -1) { match_[u] = v; match_[v] = u; break; }
        }
    }
    cout << "matched pairs after greedy:" << endl;
    for (int u = 0; u < 7; u++)
        if (match_[u] > u) cout << "  " << u << "-" << match_[u] << endl;
    cout << "exposed: ";
    for (int u = 0; u < 7; u++) if (match_[u] == -1) cout << u << " ";
    cout << endl << "(full Edmonds BFS would contract odd cycles as blossoms" << endl;
    cout << " when the alternating tree closes an odd loop)" << endl;
}
