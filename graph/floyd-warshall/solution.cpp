// sortsort · Floyd-Warshall
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/floyd-warshall

#include <bits/stdc++.h>
using namespace std;

const long long INF = numeric_limits<long long>::max() / 2;

void floydWarshall(vector<vector<long long>>& dist) {
    int n = dist.size();
    // dist[i][j] = shortest path from i to j through intermediate vertices
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
}

int main() {
    // Directed weighted graph, INF = no direct edge
    vector<vector<long long>> dist = {
        {0, 3, INF, 7},
        {8, 0, 2, INF},
        {5, INF, 0, 1},
        {2, INF, INF, 0},
    };

    floydWarshall(dist);

    cout << "All-pairs shortest paths:" << endl;
    for (const auto& row : dist) {
        for (long long value : row) {
            if (value >= INF) cout << "  INF";
            else cout << "  " << value;
        }
        cout << endl;
    }
    return 0;
}
