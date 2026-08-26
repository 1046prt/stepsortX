// Stepsort · Course Schedule
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/course-schedule

#include <bits/stdc++.h>
using namespace std;

// Kahn topological sort: possible iff no cycle remains
bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
    vector<vector<int>> graph(numCourses);
    vector<int> indegree(numCourses, 0);
    for (auto& p : prerequisites) {
        graph[p[1]].push_back(p[0]);
        indegree[p[0]]++;
    }
    queue<int> q;
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) q.push(i);
    }
    int processed = 0;
    while (!q.empty()) {
        int node = q.front();
        q.pop();
        processed++;
        for (int nxt : graph[node]) {
            if (--indegree[nxt] == 0) q.push(nxt);
        }
    }
    return processed == numCourses;
}

int main() {
    vector<vector<int>> ok = {{1, 0}};
    vector<vector<int>> cycle = {{1, 0}, {0, 1}};
    cout << boolalpha << canFinish(2, ok) << endl;
    cout << boolalpha << canFinish(2, cycle) << endl;
    return 0;
}
