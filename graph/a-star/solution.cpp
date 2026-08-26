// Stepsort · A* Search
// Category: Graph
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/a-star

#include <bits/stdc++.h>
using namespace std;

struct HeapNode {
    int f, g, id;
};

struct HeapCmp {
    bool operator()(const HeapNode& a, const HeapNode& b) const {
        return a.f > b.f;  // min-heap on f = g + h
    }
};

pair<vector<int>, int> aStar(int rows, int cols,
                             const set<pair<int,int>>& blocked,
                             int start, int goal) {
    int n = rows * cols;
    vector<int> gCost(n, INT_MAX), parent(n, -1);
    priority_queue<HeapNode, vector<HeapNode>, HeapCmp> open;
    gCost[start] = 0;
    open.push({abs(start / cols - goal / cols) +
               abs(start % cols - goal % cols), 0, start});
    int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};

    while (!open.empty()) {
        HeapNode cur = open.top();
        open.pop();
        if (cur.g > gCost[cur.id]) continue;  // stale entry
        if (cur.id == goal) {
            vector<int> path;
            for (int v = goal; v != -1; v = parent[v]) path.push_back(v);
            reverse(path.begin(), path.end());
            return {path, gCost[goal]};
        }
        int r = cur.id / cols, c = cur.id % cols;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
            if (blocked.count(make_pair(nr, nc))) continue;
            int nxt = nr * cols + nc, ng = cur.g + 1;
            if (ng < gCost[nxt]) {
                gCost[nxt] = ng;
                parent[nxt] = cur.id;
                int h = abs(nr - goal / cols) + abs(nc - goal % cols);
                open.push({ng + h, ng, nxt});  // Manhattan heuristic
            }
        }
    }
    return {{}, -1};
}

int main() {
    int rows = 4, cols = 5;
    set<pair<int,int>> blocked = {{1,1}, {1,3}, {2,2}, {3,1}};
    pair<vector<int>, int> result = aStar(rows, cols, blocked, 0, 19);

    cout << "Path:";
    for (int v : result.first) cout << " " << v;
    cout << endl;
    cout << "Cost: " << result.second << endl;
    return 0;
}
