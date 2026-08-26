// sortsort · Konig's Min Vertex Cover
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/konig-vertex-cover

#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;
vector<int> matchR;
vector<bool> vis;

bool tryKuhn(int u) {
    for (int v : adj[u]) {
        if (vis[v]) continue;
        vis[v] = true;
        if (matchR[v] == -1 || tryKuhn(matchR[v])) {
            matchR[v] = u;
            return true;
        }
    }
    return false;
}

void altDfs(int u, vector<bool>& sl, vector<bool>& sr) {
    sl[u] = true;
    for (int v : adj[u]) {
        if (sr[v]) continue;
        sr[v] = true;
        if (matchR[v] != -1 && !sl[matchR[v]]) altDfs(matchR[v]);
    }
}

int main() {
    int nL = 3, nR = 3;
    adj = {{0, 1}, {0}, {1, 2}};   // R indices 0..2
    matchR.assign(nR, -1);

    int matching = 0;
    for (int u = 0; u < nL; u++) {
        vis.assign(nR, false);
        if (tryKuhn(u)) matching++;
    }
    vector<bool> seenL(nL, false), seenR(nR, false);
    vector<bool> matchedLeft(nL, false);
    for (int v = 0; v < nR; v++) if (matchR[v] != -1) matchedLeft[matchR[v]] = true;
    for (int u = 0; u < nL; u++) if (!matchedLeft[u]) altDfs(u);

    vector<int> coverL, coverR;
    for (int u = 0; u < nL; u++) if (!seenL[u]) coverL.push_back(u);
    for (int v = 0; v < nR; v++) if (seenR[v]) coverR.push_back(v);

    cout << "matching=" << matching << ", cover size=" << coverL.size() + coverR.size() << endl;
}
