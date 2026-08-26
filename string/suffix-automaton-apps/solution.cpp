// sortsort · Suffix Automaton Applications
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton-apps

struct SAM {
    struct State { int len, link; map<char,int> next; };
    vector<State> st;
    int last;
    SAM(string s) : last(0) { st.push_back({0, -1, {}}); for (char c : s) extend(c); }
    void extend(char c) {
        int p = last, cur = st.size();
        st.push_back({st[p].len + 1, 0, {}});
        while (p >= 0 && !st[p].next.count(c)) { st[p].next[c] = cur; p = st[p].link; }
        if (p == -1) st[cur].link = 0;
        else {
            int q = st[p].next[c];
            if (st[p].len + 1 == st[q].len) st[cur].link = q;
            else {
                int clone = st.size();
                st.push_back({st[p].len + 1, st[q].link, st[q].next});
                while (p >= 0 && st[p].next[c] == q) { st[p].next[c] = clone; p = st[p].link; }
                st[q].link = st[cur].link = clone;
            }
        }
        last = cur;
    }
    long long distinctSubstrings() {
        long long total = 0;
        for (int i = 1; i < (int)st.size(); i++)
            total += st[i].len - st[st[i].link].len;
        return total;
    }
};
