// Stepsort · Bidirectional Search
// Category: Searching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bidirectional-search

#include <bits/stdc++.h>
using namespace std;

map<int, vector<int>> graph = {
    {0, {1, 2}},
    {1, {0, 3, 4}},
    {2, {0, 5, 6}},
    {3, {1, 7}},
    {4, {1, 7}},
    {5, {2}},
    {6, {2}},
    {7, {3, 4}},
};

// Expand one whole BFS level; return a node the other side saw, or -1.
int expandLevel(queue<int>& q, map<int, int>& mine, const map<int, int>& theirs) {
    int level = (int)q.size();
    for (int k = 0; k < level; k++) {
        int node = q.front();
        q.pop();
        for (int nxt : graph[node]) {
            if (!mine.count(nxt)) {
                mine[nxt] = node;
                q.push(nxt);
                if (theirs.count(nxt)) return nxt;
            }
        }
    }
    return -1;
}

// Stitch the source-to-meet and meet-to-target halves together.
vector<int> buildPath(const map<int, int>& srcParent,
                      const map<int, int>& dstParent, int meet) {
    vector<int> path;
    for (int v = meet; v != -1; v = srcParent.at(v)) path.push_back(v);
    reverse(path.begin(), path.end());
    for (int v = dstParent.at(meet); v != -1; v = dstParent.at(v)) path.push_back(v);
    return path;
}

int main() {
    int source = 0, target = 7;
    map<int, int> srcParent{{source, -1}};
    map<int, int> dstParent{{target, -1}};
    queue<int> sq, dq;
    sq.push(source);
    dq.push(target);
    int meet = -1;
    while (meet == -1 && !sq.empty() && !dq.empty()) {
        if (sq.size() <= dq.size()) meet = expandLevel(sq, srcParent, dstParent);
        else meet = expandLevel(dq, dstParent, srcParent);
    }
    cout << "meeting node: " << meet << endl;
    cout << "path found: " << (meet != -1 ? "true" : "false") << endl;
    if (meet != -1) {
        cout << "path:";
        for (int v : buildPath(srcParent, dstParent, meet)) cout << " " << v;
        cout << endl;
    }
    return 0;
}
