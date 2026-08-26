// Stepsort · Aho-Corasick
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick

#include <bits/stdc++.h>
using namespace std;

const int ALPHA = 26;

void buildAutomaton(const vector<string>& patterns,
                    vector<array<int, ALPHA>>& go,
                    vector<int>& fail,
                    vector<vector<int>>& out) {
    array<int, ALPHA> blank;
    blank.fill(-1);
    go.assign(1, blank);
    out.assign(1, {});

    for (int idx = 0; idx < (int)patterns.size(); idx++) {
        int node = 0;
        for (char ch : patterns[idx]) {
            int c = ch - 'a';
            if (go[node][c] == -1) {
                go.push_back(blank);
                out.push_back({});
                go[node][c] = go.size() - 1;
            }
            node = go[node][c];
        }
        out[node].push_back(idx);
    }

    fail.assign(go.size(), 0);
    queue<int> q;
    for (int c = 0; c < ALPHA; c++) {
        if (go[0][c] == -1) go[0][c] = 0;
        else q.push(go[0][c]);
    }
    while (!q.empty()) {
        int r = q.front();
        q.pop();
        for (int c = 0; c < ALPHA; c++) {
            int u = go[r][c];
            if (u == -1) {
                go[r][c] = go[fail[r]][c];
            } else {
                fail[u] = go[fail[r]][c];
                if (fail[u] == u) fail[u] = 0;
                out[u].insert(out[u].end(), out[fail[u]].begin(), out[fail[u]].end());
                q.push(u);
            }
        }
    }
}

void search(const string& text, const vector<string>& patterns,
            const vector<array<int, ALPHA>>& go,
            const vector<vector<int>>& out) {
    int state = 0;
    for (int i = 0; i < (int)text.size(); i++) {
        state = go[state][text[i] - 'a'];
        for (int idx : out[state]) {
            cout << patterns[idx] << " at index "
                 << i - (int)patterns[idx].size() + 1 << endl;
        }
    }
}

int main() {
    string text = "ahishers";
    vector<string> patterns = {"he", "she", "his", "hers"};
    vector<array<int, ALPHA>> go;
    vector<int> fail;
    vector<vector<int>> out;
    buildAutomaton(patterns, go, fail, out);
    cout << "text: " << text << endl;
    search(text, patterns, go, out);
    return 0;
}
