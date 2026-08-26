// Stepsort · Cycle Detection
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cycle-detection-graph

#include <bits/stdc++.h>
using namespace std;

enum Color { WHITE, GRAY, BLACK };

// Three-color DFS: white = unvisited, gray = in current stack, black = done
bool dfsCycle(int u, const vector<vector<int>>& adj, vector<int>& color) {
    color[u] = GRAY;
    for (int v : adj[u]) {
        if (color[v] == GRAY) return true;  // back edge into current path
        if (color[v] == WHITE && dfsCycle(v, adj, color)) return true;
    }
    color[u] = BLACK;
    return false;
}

bool hasCycleDirected(int V, const vector<vector<int>>& adj) {
    vector<int> color(V, WHITE);
    for (int u = 0; u < V; u++)
        if (color[u] == WHITE && dfsCycle(u, adj, color)) return true;
    return false;
}

int main() {
    vector<vector<int>> cyclicGraph = {
        {1},
        {2},
        {0, 3},  // 0 -> 1 -> 2 -> 0 forms a cycle
        {},
    };
    vector<vector<int>> acyclicGraph = {
        {1, 2},
        {3},
        {3},
        {},
    };

    cout << boolalpha;
    cout << "Graph 1 cyclic: " << hasCycleDirected(4, cyclicGraph) << endl;
    cout << "Graph 2 cyclic: " << hasCycleDirected(4, acyclicGraph) << endl;
    return 0;
}
