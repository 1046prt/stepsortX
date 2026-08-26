// Stepsort · Aho-Corasick Failure Links
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick-failure-links

#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<string> patterns = {"he", "she", "his", "hers"};
    vector<map<char,int>> go(1);
    vector<int> fail(1, 0);
    vector<vector<string>> out(1);

    auto newNode = [&]() {
        go.push_back({}); fail.push_back(0); out.push_back({});
        return (int)go.size() - 1;
    };
    for (auto& p : patterns) {
        int cur = 0;
        for (char c : p) {
            if (!go[cur].count(c)) go[cur][c] = newNode();
            cur = go[cur][c];
        }
        out[cur].push_back(p);
    }
    queue<int> q;
    for (auto& [c, v] : go[0]) { fail[v] = 0; q.push(v); }
    while (!q.empty()) {
        int u = q.front(); q.pop();
        for (auto& [c, v] : go[u]) {
            int f = fail[u];
            while (f && !go[f].count(c)) f = fail[f];
            fail[v] = go[f].count(c) ? go[f][c] : 0;
            for (auto& o : out[fail[v]]) out[v].push_back(o);
            q.push(v);
        }
    }
    string text = "ushers";
    int cur = 0;
    for (int i = 0; i < (int)text.size(); i++) {
        char c = text[i];
        while (cur && !go[cur].count(c)) cur = fail[cur];
        cur = go[cur].count(c) ? go[cur][c] : 0;
        for (auto& p : out[cur])
            cout << p << " @ " << (i - p.size() + 1) << ".." << i << endl;
    }
}
