// sortsort · Red-Black Tree
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/red-black-tree

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int key;
    bool red;   // new nodes start red
    Node *left, *right, *parent;
    explicit Node(int k) : key(k), red(true), left(nullptr), right(nullptr), parent(nullptr) {}
};

struct RedBlackTree {
    Node* root = nullptr;

    void rotateLeft(Node* x) {
        Node* y = x->right;
        x->right = y->left;
        if (y->left) y->left->parent = x;
        y->parent = x->parent;
        if (!x->parent) root = y;
        else if (x == x->parent->left) x->parent->left = y;
        else x->parent->right = y;
        y->left = x;
        x->parent = y;
    }

    void rotateRight(Node* x) {
        Node* y = x->left;
        x->left = y->right;
        if (y->right) y->right->parent = x;
        y->parent = x->parent;
        if (!x->parent) root = y;
        else if (x == x->parent->left) x->parent->left = y;
        else x->parent->right = y;
        y->right = x;
        x->parent = y;
    }

    void insert(int key) {
        Node* z = new Node(key);
        Node *parent = nullptr, *cur = root;
        while (cur) {                       // ordinary BST descent
            parent = cur;
            cur = key < cur->key ? cur->left : cur->right;
        }
        z->parent = parent;
        if (!parent) root = z;
        else if (key < parent->key) parent->left = z;
        else parent->right = z;
        fixup(z);
    }

    void fixup(Node* z) {
        while (z->parent && z->parent->red) {
            Node* gp = z->parent->parent;
            if (z->parent == gp->left) {
                Node* u = gp->right;
                if (u && u->red) {          // red uncle: recolor only
                    z->parent->red = u->red = false;
                    gp->red = true;
                    z = gp;
                } else {
                    if (z == z->parent->right) { z = z->parent; rotateLeft(z); }
                    z->parent->red = false; // line: rotate grandparent
                    gp->red = true;
                    rotateRight(gp);
                }
            } else {                        // mirror image
                Node* u = gp->left;
                if (u && u->red) {
                    z->parent->red = u->red = false;
                    gp->red = true;
                    z = gp;
                } else {
                    if (z == z->parent->left) { z = z->parent; rotateRight(z); }
                    z->parent->red = false;
                    gp->red = true;
                    rotateLeft(gp);
                }
            }
        }
        root->red = false;
    }

    void inorder(Node* n) const {
        if (!n) return;
        inorder(n->left);
        cout << n->key << ' ';
        inorder(n->right);
    }
};

int main() {
    RedBlackTree t;
    for (int k : {10, 20, 30, 15, 25, 5, 1, 40, 35}) t.insert(k);
    cout << "Inorder (must be sorted): ";
    t.inorder(t.root);
    cout << endl;
    cout << "Root: " << t.root->key << (t.root->red ? " RED" : " BLACK") << endl;
    return 0;
}
