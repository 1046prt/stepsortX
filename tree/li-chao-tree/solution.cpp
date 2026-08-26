// Stepsort · Li Chao Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/li-chao-tree

#include <bits/stdc++.h>
using namespace std;

const int LO = -5, HI = 5;

struct Node {
    long long m, b;
    Node* left = nullptr;
    Node* right = nullptr;
};

long long f(long long m, long long b, long long x) { return m * x + b; }

Node* insertLine(Node* node, int l, int r, long long m, long long b) {
    if (!node) {
        Node* fresh = new Node();
        fresh->m = m;
        fresh->b = b;
        return fresh;
    }
    int mid = (l + r) / 2;
    bool leftWin = f(m, b, l) < f(node->m, node->b, l);
    bool middleWin = f(m, b, mid) < f(node->m, node->b, mid);
    if (middleWin) { swap(node->m, m); swap(node->b, b); }
    if (l == r) return node;
    if (leftWin != middleWin) node->left = insertLine(node->left, l, mid, m, b);
    else node->right = insertLine(node->right, mid + 1, r, m, b);
    return node;
}

long long query(Node* node, int l, int r, int x) {
    long long best = f(node->m, node->b, x);
    while (l < r) {
        int mid = (l + r) / 2;
        if (x <= mid) { node = node->left; r = mid; }
        else { node = node->right; l = mid + 1; }
        if (!node) break;
        best = min(best, f(node->m, node->b, x));
    }
    return best;
}

int main() {
    Node* root = nullptr;
    root = insertLine(root, LO, HI, 1, 0);
    root = insertLine(root, LO, HI, -1, 6);
    root = insertLine(root, LO, HI, 0, -2);
    cout << "min at x = -3: " << query(root, LO, HI, -3) << endl;
    cout << "min at x = 4: " << query(root, LO, HI, 4) << endl;
}
