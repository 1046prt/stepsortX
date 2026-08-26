// Stepsort · M-Coloring Problem
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/graph-coloring-bt

#include <bits/stdc++.h>
using namespace std;

const int V = 4;
int graph[V][V] = {
    {0, 1, 0, 1},
    {1, 0, 1, 0},
    {0, 1, 0, 1},
    {1, 0, 1, 0}
};
int m = 3;
int colors[V];

bool isSafe(int v, int c) {
    for (int u = 0; u < V; u++) {
        if (graph[v][u] == 1 && colors[u] == c) return false;
    }
    return true;
}

bool colorGraph(int v) {
    if (v == V) return true;
    for (int c = 1; c <= m; c++) {
        if (isSafe(v, c)) {
            colors[v] = c;
            if (colorGraph(v + 1)) return true;
            colors[v] = 0;
        }
    }
    return false;
}

int main() {
    fill(colors, colors + V, 0);
    if (colorGraph(0)) {
        cout << "Color assignment:";
        for (int v = 0; v < V; v++) cout << " " << colors[v];
        cout << endl;
    } else {
        cout << "Not possible with " << m << " colors" << endl;
    }
    return 0;
}
