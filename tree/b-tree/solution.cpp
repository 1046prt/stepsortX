// Stepsort · B-Tree Operations
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/b-tree

#include <bits/stdc++.h>
using namespace std;

struct BTreeNode {
    vector<int> keys;
    vector<BTreeNode*> children;
    bool leaf;
    explicit BTreeNode(bool lf) : leaf(lf) {}
};

struct BTree {
    int t;                  // minimum degree
    BTreeNode* root;
    explicit BTree(int deg) : t(deg), root(new BTreeNode(true)) {}

    void splitChild(BTreeNode* x, int i) {
        BTreeNode* y = x->children[i];
        BTreeNode* z = new BTreeNode(y->leaf);
        int mid = y->keys[t - 1];
        z->keys.assign(y->keys.begin() + t, y->keys.end());
        y->keys.resize(t - 1);
        if (!y->leaf) {
            z->children.assign(y->children.begin() + t, y->children.end());
            y->children.resize(t);
        }
        x->children.insert(x->children.begin() + i + 1, z);
        x->keys.insert(x->keys.begin() + i, mid);
    }

    void insertNonFull(BTreeNode* x, int k) {
        if (x->leaf) {
            int pos = x->keys.size();
            while (pos > 0 && k < x->keys[pos - 1]) pos--;
            x->keys.insert(x->keys.begin() + pos, k);   // keep sorted
            return;
        }
        int i = x->keys.size() - 1;
        while (i >= 0 && k < x->keys[i]) i--;
        i++;
        // Preemptive split: never descend into a full child.
        if ((int)x->children[i]->keys.size() == 2 * t - 1) {
            splitChild(x, i);
            if (k > x->keys[i]) i++;
        }
        insertNonFull(x->children[i], k);
    }

    void insert(int k) {
        if ((int)root->keys.size() == 2 * t - 1) {
            BTreeNode* s = new BTreeNode(false);
            s->children.push_back(root);
            root = s;
            splitChild(s, 0);
            insertNonFull(s, k);
        } else {
            insertNonFull(root, k);
        }
    }

    void traverse(BTreeNode* x) {
        for (size_t i = 0; i < x->keys.size(); i++) {
            if (!x->leaf) traverse(x->children[i]);
            cout << x->keys[i] << ' ';
        }
        if (!x->leaf) traverse(x->children.back());
    }
};

int main() {
    BTree bt(3);
    for (int v : {10, 20, 5, 6, 12, 30, 7, 17, 3, 25, 1, 40, 8}) bt.insert(v);
    cout << "B-Tree traversal (t = 3):" << endl;
    bt.traverse(bt.root);
    cout << endl;
    return 0;
}
