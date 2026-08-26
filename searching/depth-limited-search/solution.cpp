// Stepsort · Depth-Limited Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/depth-limited-search

#include <bits/stdc++.h>
using namespace std;

// DFS capped at the depth limit; fills path and returns true on success.
bool dls(const map<int, vector<int>>& graph, int node, int target,
         int limit, vector<int>& path) {
    path.push_back(node);
    if (node == target) return true;
    if ((int)path.size() - 1 < limit) {
        auto it = graph.find(node);
        if (it != graph.end()) {
            for (int nxt : it->second) {
                if (find(path.begin(), path.end(), nxt) == path.end()
                        && dls(graph, nxt, target, limit, path)) {
                    return true;
                }
            }
        }
    }
    path.pop_back();
    return false;
}

int main() {
    map<int, vector<int>> graph = {
        {0, {1, 2}}, {1, {3, 4}}, {2, {5, 6}},
        {3, {7}}, {4, {7}}, {5, {}}, {6, {}}, {7, {}},
    };
    vector<int> path;
    if (dls(graph, 0, 6, 2, path)) {
        cout << "limit 2 to node 6, path:";
        for (int v : path) cout << " " << v;
        cout << endl;
    } else {
        cout << "node 6 not reached within limit 2" << endl;
    }
    path.clear();
    if (dls(graph, 0, 7, 1, path)) {
        cout << "limit 1 to node 7, path:";
        for (int v : path) cout << " " << v;
        cout << endl;
    } else {
        cout << "node 7 not reached within limit 1" << endl;
    }
    return 0;
}
