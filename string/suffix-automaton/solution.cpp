// sortsort · Suffix Automaton
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton

#include <bits/stdc++.h>
using namespace std;

struct SuffixAutomaton {
    struct State { int len, link; map<char, int> next; };
    vector<State> st;
    int last = 0;

    SuffixAutomaton(const string& s) {
        st.push_back({0, -1, {}});
        for (char c : s) extend(c);
    }

    void extend(char c) {
        int cur = st.size();
        st.push_back({st[last].len + 1, -1, {}});
        int p = last;
        while (p != -1 && !st[p].next.count(c)) {
            st[p].next[c] = cur;
            p = st[p].link;
        }
        if (p == -1) {
            st[cur].link = 0;
        } else {
            int q = st[p].next[c];
            if (st[q].len == st[p].len + 1) {
                st[cur].link = q;
            } else {
                int clone = st.size();
                st.push_back({st[p].len + 1, st[q].link, st[q].next});
                while (p != -1 && st[p].next[c] == q) {
                    st[p].next[c] = clone;
                    p = st[p].link;
                }
                st[q].link = clone;
                st[cur].link = clone;
            }
        }
        last = cur;
    }

    long long countDistinctSubstrings() {
        long long total = 0;
        for (size_t i = 1; i < st.size(); i++)
            total += st[i].len - st[st[i].link].len;
        return total;
    }
};

int main() {
    SuffixAutomaton sam("abab");
    cout << "states: " << sam.st.size() << endl;                  // 6
    cout << "distinct substrings: " << sam.countDistinctSubstrings() << endl;  // 7
}
