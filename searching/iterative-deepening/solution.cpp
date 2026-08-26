// sortsort · Iterative Deepening DFS
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/iterative-deepening

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

// Re-run depth-limited search with limits 0..maxLimit until found.
pair<bool, int> iterativeDeepening(const map<int, vector<int>>& graph,
                                   int start, int target, int maxLimit,
                                   vector<int>& path) {
    for (int limit = 0; limit <= maxLimit; limit++) {
        path.clear();
        if (dls(graph, start, target, limit, path)) return {true, limit};
    }
    path.clear();
    return {false, -1};
}

int main() {
    map<int, vector<int>> graph = {
        {0, {1, 2}}, {1, {3, 4}}, {2, {5, 6}},
        {3, {7}}, {4, {7}}, {5, {}}, {6, {}}, {7, {}},
    };
    vector<int> path;
    auto res = iterativeDeepening(graph, 0, 7, 4, path);
    cout << "target 7 found: " << (res.first ? "true" : "false")
         << " at depth " << res.second << ", path:";
    for (int v : path) cout << " " << v;
    cout << endl;
    res = iterativeDeepening(graph, 0, 5, 4, path);
    cout << "target 5 found: " << (res.first ? "true" : "false")
         << " at depth " << res.second << ", path:";
    for (int v : path) cout << " " << v;
    cout << endl;
    return 0;
}
