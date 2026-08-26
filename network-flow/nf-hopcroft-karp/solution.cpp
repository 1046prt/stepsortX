// sortsort · Hopcroft-Karp
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hopcroft-karp

#include <bits/stdc++.h>
using namespace std;

int numLeft;
vector<vector<int>> adj;
vector<int> matchLeft, matchRight, layerDist;

bool bfsLayers() {
    queue<int> q;
    for (int u = 1; u <= numLeft; ++u) {
        if (matchLeft[u] == 0) {
            layerDist[u] = 0;
            q.push(u);
        } else {
            layerDist[u] = INT_MAX;
        }
    }
    bool foundFree = false;
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (int v : adj[u]) {
            int w = matchRight[v];
            if (w == 0) foundFree = true;
            else if (layerDist[w] == INT_MAX) {
                layerDist[w] = layerDist[u] + 1;
                q.push(w);
            }
        }
    }
    return foundFree;
}

bool dfsAugment(int u) {
    for (int v : adj[u]) {
        int w = matchRight[v];
        if (w == 0 || (layerDist[w] == layerDist[u] + 1 && dfsAugment(w))) {
            matchLeft[u] = v;
            matchRight[v] = u;
            return true;
        }
    }
    layerDist[u] = INT_MAX;
    return false;
}

int main() {
    numLeft = 4;
    adj.assign(numLeft + 1, {});
    adj[1] = {1, 2};
    adj[2] = {1, 3};
    adj[3] = {2, 4};
    adj[4] = {3};
    matchLeft.assign(numLeft + 1, 0);
    matchRight.assign(5, 0);
    layerDist.assign(numLeft + 1, 0);

    int matching = 0;
    while (bfsLayers()) {
        for (int u = 1; u <= numLeft; ++u) {
            if (matchLeft[u] == 0 && dfsAugment(u)) matching++;
        }
    }
    cout << "Maximum matching size: " << matching << endl;
    for (int u = 1; u <= numLeft; ++u) {
        if (matchLeft[u] != 0) {
            cout << "Left " << u << " matched with Right " << matchLeft[u] << endl;
        }
    }
    return 0;
}
