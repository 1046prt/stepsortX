// sortsort · Graph Coloring
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring

#include <bits/stdc++.h>
using namespace std;

// Assign each vertex the smallest color not used by its colored neighbors
vector<int> greedyColoring(int V, const vector<vector<int>>& adj) {
    vector<int> result(V, -1);
    for (int u = 0; u < V; u++) {
        vector<bool> used(V, false);
        for (int v : adj[u])
            if (result[v] != -1) used[result[v]] = true;
        int color = 0;
        while (used[color]) color++;
        result[u] = color;
    }
    return result;
}

int main() {
    int V = 5;
    // Undirected triangle 0-1-2 plus tail 3-4
    vector<vector<int>> adj = {
        {1, 2},
        {0, 2, 3},
        {0, 1, 3},
        {1, 2, 4},
        {3},
    };

    vector<int> colors = greedyColoring(V, adj);
    for (int v = 0; v < V; v++)
        cout << "Vertex " << v << " -> color " << colors[v] << endl;
    cout << "Total colors used: "
         << *max_element(colors.begin(), colors.end()) + 1 << endl;
    return 0;
}
