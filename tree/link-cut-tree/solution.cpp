// sortsort · Link-Cut Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/link-cut-tree

#include <bits/stdc++.h>
using namespace std;

vector<int> parentArr = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
vector<int> preferredChild(12, -1);

void access(int x) {
    cout << "access(" << x << "):" << endl;
    int v = x;
    while (parentArr[v] != -1) {
        int p = parentArr[v];
        int old = preferredChild[p];
        if (old != -1 && old != v)
            cout << "  CUT " << p << "->" << old << endl;
        preferredChild[p] = v;
        cout << "  LINK " << p << "->" << v << endl;
        v = p;
    }
}

int main() {
    access(11);
    access(9);
    access(6);
}
