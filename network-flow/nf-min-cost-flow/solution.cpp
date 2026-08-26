// sortsort · Min-Cost Max Flow
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-min-cost-flow

#include <bits/stdc++.h>
using namespace std;

struct FlowEdge {
    int to, cap, cost, rev;
};

class MinCostFlow {
   public:
    explicit MinCostFlow(int n) : graph(n) {}

    void addEdge(int u, int v, int cap, int cost) {
        graph[u].push_back({v, cap, cost, (int)graph[v].size()});
        graph[v].push_back({u, 0, -cost, (int)graph[u].size() - 1});
    }

    pair<int, int> minCostMaxFlow(int s, int t, int maxPush) {
        const int INF = INT_MAX / 2;
        int n = (int)graph.size();
        int flow = 0, totalCost = 0;
        while (flow < maxPush) {
            vector<int> dist(n, INF), prevNode(n, -1), prevEdge(n, -1);
            vector<bool> inQueue(n, false);
            queue<int> q;
            dist[s] = 0;
            q.push(s);
            inQueue[s] = true;
            while (!q.empty()) {
                int u = q.front();
                q.pop();
                inQueue[u] = false;
                for (int i = 0; i < (int)graph[u].size(); ++i) {
                    FlowEdge& e = graph[u][i];
                    if (e.cap > 0 && dist[u] + e.cost < dist[e.to]) {
                        dist[e.to] = dist[u] + e.cost;
                        prevNode[e.to] = u;
                        prevEdge[e.to] = i;
                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            q.push(e.to);
                        }
                    }
                }
            }
            if (dist[t] >= INF) break;
            int push = maxPush - flow;
            for (int v = t; v != s; v = prevNode[v]) {
                push = min(push, graph[prevNode[v]][prevEdge[v]].cap);
            }
            for (int v = t; v != s; v = prevNode[v]) {
                FlowEdge& e = graph[prevNode[v]][prevEdge[v]];
                e.cap -= push;
                graph[v][e.rev].cap += push;
            }
            flow += push;
            totalCost += push * dist[t];
        }
        return {flow, totalCost};
    }

   private:
    vector<vector<FlowEdge>> graph;
};

int main() {
    MinCostFlow net(4);
    vector<vector<int>> edges = {
        {0, 1, 2, 2},
        {0, 2, 1, 3},
        {1, 3, 1, 4},
        {1, 2, 1, 1},
        {2, 3, 2, 1}
    };
    for (const auto& e : edges) net.addEdge(e[0], e[1], e[2], e[3]);
    pair<int, int> result = net.minCostMaxFlow(0, 3, 100);
    cout << "Flow sent: " << result.first << endl;
    cout << "Total cost: " << result.second << endl;
    return 0;
}
