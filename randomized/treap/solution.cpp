// Stepsort · Treap
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/treap

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int key, pri;
    Node* left = nullptr;
    Node* right = nullptr;
    Node(int k, int p) : key(k), pri(p) {}
};

Node* rotateRight(Node* t) {
    Node* l = t->left;
    t->left = l->right;
    l->right = t;
    return l;
}

Node* rotateLeft(Node* t) {
    Node* r = t->right;
    t->right = r->left;
    r->left = t;
    return r;
}

Node* insert(Node* t, int key, int pri) {
    if (!t) return new Node(key, pri);
    if (key < t->key) {
        t->left = insert(t->left, key, pri);
        if (t->left->pri > t->pri) t = rotateRight(t);
    } else if (key > t->key) {
        t->right = insert(t->right, key, pri);
        if (t->right->pri > t->pri) t = rotateLeft(t);
    }
    return t;
}

void inorder(Node* t, vector<int>& out) {
    if (!t) return;
    inorder(t->left, out);
    out.push_back(t->key);
    inorder(t->right, out);
}

void printInorder(Node* root) {
    vector<int> out;
    inorder(root, out);
    for (size_t i = 0; i < out.size(); i++) {
        cout << out[i];
        if (i + 1 < out.size()) cout << " ";
    }
    cout << endl;
}

int main() {
    Node* root = nullptr;
    int items[][2] = {{50, 9}, {30, 14}, {70, 4}, {20, 16}, {40, 7}};
    for (auto& kp : items) {
        root = insert(root, kp[0], kp[1]);
        cout << "after inserting (" << kp[0] << ", " << kp[1] << "): ";
        printInorder(root);
    }
}
