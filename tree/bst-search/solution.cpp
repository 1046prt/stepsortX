// Stepsort · BST Search
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-search

// Binary Search Tree search: returns true/false, prints the visited path
#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node *left;
    Node *right;
    Node(int v) : val(v), left(nullptr), right(nullptr) {}
};

Node* insert(Node* root, int val) {
    if (root == nullptr) return new Node(val);
    if (val < root->val) root->left = insert(root->left, val);
    else root->right = insert(root->right, val);
    return root;
}

bool search(Node* root, int target) {
    // Walk down from the root, comparing against each visited node.
    vector<int> path;
    bool found = false;
    Node* cur = root;
    while (cur != nullptr) {
        path.push_back(cur->val);
        if (target == cur->val) { found = true; break; }
        cur = (target < cur->val) ? cur->left : cur->right;
    }
    cout << "search " << target << " : path";
    for (int v : path) cout << " " << v;
    cout << " -> " << (found ? "FOUND" : "NOT FOUND") << endl;
    return found;
}

int main() {
    Node* root = nullptr;
    for (int v : {50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);
    for (int t : {40, 65, 80}) search(root, t);
    return 0;
}
