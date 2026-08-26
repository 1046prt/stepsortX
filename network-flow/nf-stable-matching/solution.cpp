// Stepsort · Gale-Shapley (Stable Matching)
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-stable-matching

#include <bits/stdc++.h>
using namespace std;

vector<int> galeShapley(const vector<vector<int>>& menPref,
                        const vector<vector<int>>& womenPref) {
    int n = (int)menPref.size();
    vector<int> nextChoice(n, 0), fiance(n, -1);
    vector<int> freeList;
    for (int i = n - 1; i >= 0; --i) freeList.push_back(i);
    while (!freeList.empty()) {
        int man = freeList.back();
        freeList.pop_back();
        int woman = menPref[man][nextChoice[man]++];
        int rival = fiance[woman];
        bool prefersNew = rival == -1 ||
                          find(womenPref[woman].begin(), womenPref[woman].end(), man) <
                              find(womenPref[woman].begin(), womenPref[woman].end(), rival);
        if (prefersNew) {
            fiance[woman] = man;
            if (rival != -1) freeList.push_back(rival);
        } else {
            freeList.push_back(man);
        }
    }
    vector<int> partner(n, -1);
    for (int w = 0; w < n; ++w) partner[fiance[w]] = w;
    return partner;
}

bool isStable(const vector<vector<int>>& menPref, const vector<vector<int>>& womenPref,
              const vector<int>& partner) {
    int n = (int)partner.size();
    for (int m = 0; m < n; ++m) {
        int w = partner[m];
        for (int w2 = 0; w2 < n; ++w2) {
            if (w2 == w) continue;
            int m2 = (int)(find(partner.begin(), partner.end(), w2) - partner.begin());
            bool manPrefers = menPref[m][w2] < menPref[m][w];
            bool womanPrefers = womenPref[w2][m] < womenPref[w2][m2];
            if (manPrefers && womanPrefers) return false;
        }
    }
    return true;
}

int main() {
    vector<vector<int>> menPref = {
        {0, 1, 2},
        {1, 0, 2},
        {0, 1, 2}
    };
    vector<vector<int>> womenPref = {
        {1, 0, 2},
        {0, 2, 1},
        {0, 1, 2}
    };
    vector<int> partner = galeShapley(menPref, womenPref);
    for (int m = 0; m < (int)partner.size(); ++m) {
        cout << "Man " << m << " engaged to Woman " << partner[m] << endl;
    }
    cout << "Matching is stable: "
         << (isStable(menPref, womenPref, partner) ? "yes" : "no") << endl;
    return 0;
}
