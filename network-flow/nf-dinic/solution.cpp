// Stepsort · Dinic's Algorithm
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-dinic

#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int to, cap, rev;
};

class Dinic {
   public:
    explicit Dinic(int n) : graph(n), level(n), iterator_(n) {}

    void addEdge(int u, int v, int cap) {
        graph[u].push_back({v, cap, (int)graph[v].size()});
        graph[v].push_back({u, 0, (int)graph[u].size() - 1});
    }

    int maxFlow(int s, int t) {
        int flow = 0;
        while (buildLevels(s, t)) {
            fill(iterator_.begin(), iterator_.end(), 0);
            while (true) {
                int pushed = sendFlow(s, t, INT_MAX);
                if (pushed == 0) break;
                flow += pushed;
            }
        }
        return flow;
    }

   private:
    vector<vector<Edge>> graph;
    vector<int> level, iterator_;

    bool buildLevels(int s, int t) {
        fill(level.begin(), level.end(), -1);
        queue<int> q;
        level[s] = 0;
        q.push(s);
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            for (const Edge& e : graph[u]) {
                if (e.cap > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.push(e.to);
                }
            }
        }
        return level[t] != -1;
    }

    int sendFlow(int u, int t, int limit) {
        if (u == t) return limit;
        for (int& i = iterator_[u]; i < (int)graph[u].size(); ++i) {
            Edge& e = graph[u][i];
            if (e.cap > 0 && level[e.to] == level[u] + 1) {
                int pushed = sendFlow(e.to, t, min(limit, e.cap));
                if (pushed > 0) {
                    e.cap -= pushed;
                    graph[e.to][e.rev].cap += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }
};

int main() {
    Dinic dinic(6);
    vector<vector<int>> edges = {
        {0, 1, 16}, {0, 2, 13},
        {1, 3, 12},
        {2, 1, 4}, {2, 4, 14},
        {3, 2, 9}, {3, 5, 20},
        {4, 3, 7}, {4, 5, 4}
    };
    for (const auto& e : edges) dinic.addEdge(e[0], e[1], e[2]);
    cout << "Max flow: " << dinic.maxFlow(0, 5) << endl;
    return 0;
}
