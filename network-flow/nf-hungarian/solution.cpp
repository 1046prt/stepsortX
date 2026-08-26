// Stepsort · Hungarian Algorithm
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-hungarian

#include <bits/stdc++.h>
using namespace std;

pair<long long, vector<int>> hungarian(const vector<vector<int>>& cost) {
    int n = (int)cost.size();
    const long long INF = LLONG_MAX / 4;
    vector<long long> u(n + 1, 0), v(n + 1, 0), minV(n + 1);
    vector<int> p(n + 1, 0), way(n + 1, 0);
    for (int i = 1; i <= n; ++i) {
        p[0] = i;
        int col = 0;
        fill(minV.begin(), minV.end(), INF);
        vector<char> used(n + 1, 0);
        do {
            used[col] = 1;
            int row = p[col];
            long long delta = INF;
            int nextCol = -1;
            for (int j = 1; j <= n; ++j) {
                if (!used[j]) {
                    long long reduced = (long long)cost[row - 1][j - 1] - u[row] - v[j];
                    if (reduced < minV[j]) {
                        minV[j] = reduced;
                        way[j] = col;
                    }
                    if (minV[j] < delta) {
                        delta = minV[j];
                        nextCol = j;
                    }
                }
            }
            for (int j = 0; j <= n; ++j) {
                if (used[j]) {
                    u[p[j]] += delta;
                    v[j] -= delta;
                } else {
                    minV[j] -= delta;
                }
            }
            col = nextCol;
        } while (p[col] != 0);
        while (col != 0) {
            int prev = way[col];
            p[col] = p[prev];
            col = prev;
        }
    }
    vector<int> assignment(n, -1);
    for (int j = 1; j <= n; ++j) assignment[p[j] - 1] = j - 1;
    long long total = 0;
    for (int i = 0; i < n; ++i) total += cost[i][assignment[i]];
    return {total, assignment};
}

int main() {
    vector<vector<int>> cost = {
        {9, 2, 7},
        {6, 4, 3},
        {5, 8, 1}
    };
    auto result = hungarian(cost);
    cout << "Minimum total cost: " << result.first << endl;
    for (int i = 0; i < (int)result.second.size(); ++i) {
        cout << "Worker " << i << " -> Job " << result.second[i]
             << " (cost " << cost[i][result.second[i]] << ")" << endl;
    }
    return 0;
}
