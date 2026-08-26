// Stepsort · Morris Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/morris-traversal

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node *left, *right;
    explicit Node(int v) : val(v), left(nullptr), right(nullptr) {}
};

void morrisInorder(Node* root) {
    Node* cur = root;
    while (cur) {
        if (!cur->left) {
            cout << cur->val << ' ';         // no left subtree: visit
            cur = cur->right;
        } else {
            Node* pred = cur->left;
            while (pred->right && pred->right != cur) pred = pred->right;
            if (!pred->right) {
                pred->right = cur;           // create thread, go left
                cur = cur->left;
            } else {
                pred->right = nullptr;       // remove thread: restore tree
                cout << cur->val << ' ';
                cur = cur->right;
            }
        }
    }
}

Node* insert(Node* root, int v) {
    if (!root) return new Node(v);
    if (v < root->val) root->left = insert(root->left, v);
    else root->right = insert(root->right, v);
    return root;
}

int main() {
    Node* root = nullptr;
    for (int v : {50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);
    cout << "Morris inorder:" << endl;
    morrisInorder(root);
    cout << endl;
    morrisInorder(root);   // second pass proves the tree was restored
    cout << endl;
    return 0;
}
