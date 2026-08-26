// Stepsort · BST Insert
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-insert

// Binary Search Tree: iterative insert + inorder print demo
#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node *left;
    Node *right;
    Node(int v) : val(v), left(nullptr), right(nullptr) {}
};

Node* insert(Node* root, int val) {
    // Iterative insert; returns the root after insertion.
    Node* node = new Node(val);
    if (root == nullptr) return node;
    Node* cur = root;
    while (true) {
        if (val < cur->val) {
            if (cur->left == nullptr) { cur->left = node; break; }
            cur = cur->left;
        } else {
            if (cur->right == nullptr) { cur->right = node; break; }
            cur = cur->right;
        }
    }
    return root;
}

void inorder(Node* node) {
    if (node == nullptr) return;
    inorder(node->left);
    cout << node->val << " ";
    inorder(node->right);
}

int main() {
    Node* root = nullptr;
    int values[] = {50, 30, 70, 20, 40, 60, 80};
    for (int v : values) {
        root = insert(root, v);
        cout << "inserted " << v << " -> ";
        inorder(root);
        cout << endl;
    }
    return 0;
}
