// Stepsort · Bellman-Ford
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bellman-ford

#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int u, v, w;
};

// Returns false if a negative cycle is reachable from the source.
bool bellmanFord(int numVertices, const vector<Edge>& edges, int source,
                 vector<long long>& dist) {
    const long long INF = numeric_limits<long long>::max() / 2;
    dist.assign(numVertices, INF);
    dist[source] = 0;

    // Relax every edge V-1 times
    for (int pass = 0; pass < numVertices - 1; pass++) {
        bool changed = false;
        for (const Edge& e : edges) {
            if (dist[e.u] + e.w < dist[e.v]) {
                dist[e.v] = dist[e.u] + e.w;
                changed = true;
            }
        }
        if (!changed) break;
    }

    // One more improving pass means a negative cycle is reachable
    for (const Edge& e : edges) {
        if (dist[e.u] + e.w < dist[e.v]) return false;
    }
    return true;
}

int main() {
    // Directed weighted graph with 5 vertices (0..4)
    vector<Edge> edges = {
        {0, 1, 4}, {0, 2, 5}, {1, 2, -3}, {1, 3, 6}, {2, 3, 4}, {3, 4, 2}
    };

    vector<long long> dist;
    if (bellmanFord(5, edges, 0, dist)) {
        const long long INF = numeric_limits<long long>::max() / 2;
        cout << "Shortest distances from vertex 0:" << endl;
        for (long long d : dist) {
            if (d >= INF) cout << "  INF";
            else cout << "  " << d;
        }
        cout << endl;
    } else {
        cout << "Negative cycle detected" << endl;
    }
    return 0;
}
