// Stepsort · Palindromic Tree Applications
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindromic-tree-apps

struct Eertree {
    vector<array<int,26>> tree;
    vector<int> len, suffix, count;
    int last, size;
    string s;
    Eertree(string str) : last(0), size(2), s(str) {
        tree.resize(2); len = {0, -1}; suffix = {1, 0};
        count.assign(2, 0); tree[0].fill(0); tree[1].fill(0);
        for (int i = 0; i < str.size(); i++) add(str[i], i);
        for (int i = size - 1; i >= 2; i--) count[suffix[i]] += count[i];
    }
    int getLink(int v, int pos) {
        while (pos - 1 - len[v] < 0 || s[pos - 1 - len[v]] != s[pos]) v = suffix[v];
        return v;
    }
    void add(char c, int pos) {
        int cur = getLink(last, pos), ch = c - 'a';
        if (!tree[cur][ch]) {
            tree.push_back({}); tree.back().fill(0);
            len.push_back(len[cur] + 2);
            suffix.push_back(0); count.push_back(0);
            tree[cur][ch] = size;
            int q = getLink(suffix[cur], pos);
            suffix[size] = tree[q][ch] ? tree[q][ch] : 1;
            size++;
        }
        count[tree[cur][ch]]++;
        last = tree[cur][ch];
    }
    int distinct() { return size - 2; }
};
