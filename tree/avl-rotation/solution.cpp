// Stepsort · AVL Rotations
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/avl-rotation

// AVL tree insertion with LL, RR, LR and RL rotations
#include <bits/stdc++.h>
using namespace std;

struct Node {
    int key;
    int height;
    Node* left;
    Node* right;
    Node(int k) : key(k), height(1), left(nullptr), right(nullptr) {}
};

int hgt(Node* n) { return n ? n->height : 0; }

void upd(Node* n) { n->height = 1 + max(hgt(n->left), hgt(n->right)); }

int bal(Node* n) { return hgt(n->left) - hgt(n->right); }

Node* rotRight(Node* y) {
    Node* x = y->left;
    y->left = x->right;
    x->right = y;
    upd(y);
    upd(x);
    return x;
}

Node* rotLeft(Node* x) {
    Node* y = x->right;
    x->right = y->left;
    y->left = x;
    upd(x);
    upd(y);
    return y;
}

Node* insert(Node* node, int key) {
    if (!node) return new Node(key);
    if (key < node->key) node->left = insert(node->left, key);
    else if (key > node->key) node->right = insert(node->right, key);
    else return node;  // no duplicates
    upd(node);
    int b = bal(node);
    if (b > 1 && key < node->left->key) return rotRight(node);   // left-left
    if (b < -1 && key > node->right->key) return rotLeft(node);  // right-right
    if (b > 1 && key > node->left->key) {                        // left-right
        node->left = rotLeft(node->left);
        return rotRight(node);
    }
    if (b < -1 && key < node->right->key) {                      // right-left
        node->right = rotRight(node->right);
        return rotLeft(node);
    }
    return node;
}

void preorder(Node* n) {
    if (!n) return;
    cout << n->key << " ";
    preorder(n->left);
    preorder(n->right);
}

int main() {
    Node* root = nullptr;
    for (int k = 10; k >= 1; --k) root = insert(root, k);
    cout << "preorder after inserting 10..1: ";
    preorder(root);
    cout << endl;
    cout << "root height stays about log2(10) = 4: " << hgt(root) << endl;
    return 0;
}
