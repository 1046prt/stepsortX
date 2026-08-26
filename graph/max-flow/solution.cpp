// sortsort · Ford-Fulkerson Max Flow
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/max-flow

#include <bits/stdc++.h>
using namespace std;

// Ford-Fulkerson method; BFS picks shortest augmenting paths first
long long fordFulkerson(vector<vector<long long>> residual, int source, int sink) {
    int n = (int)residual.size();
    long long totalFlow = 0;
    while (true) {
        vector<int> parent(n, -1);
        parent[source] = source;
        queue<int> q;
        q.push(source);
        while (!q.empty() && parent[sink] == -1) {
            int u = q.front();
            q.pop();
            for (int v = 0; v < n; v++) {
                if (residual[u][v] > 0 && parent[v] == -1) {
                    parent[v] = u;
                    q.push(v);
                }
            }
        }
        if (parent[sink] == -1) break;  // no augmenting path remains
        long long bottleneck = LLONG_MAX;
        for (int v = sink; v != source; v = parent[v])
            bottleneck = min(bottleneck, residual[parent[v]][v]);
        for (int v = sink; v != source; v = parent[v]) {
            residual[parent[v]][v] -= bottleneck;
            residual[v][parent[v]] += bottleneck;
        }
        totalFlow += bottleneck;
    }
    return totalFlow;
}

int main() {
    vector<vector<long long>> capacity = {
        {0, 16, 13, 0, 0, 0},
        {0, 0, 10, 12, 0, 0},
        {0, 4, 0, 0, 14, 0},
        {0, 0, 9, 0, 0, 20},
        {0, 0, 0, 7, 0, 4},
        {0, 0, 0, 0, 0, 0}
    };
    cout << "Max flow: " << fordFulkerson(capacity, 0, 5) << endl;
    return 0;
}
