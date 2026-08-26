// sortsort · BST Delete
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bst-delete

// BST deletion: leaf, one-child and two-children cases.
// Two-children case swaps in the inorder successor (leftmost of right subtree).
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

Node* min_value_node(Node* node) {
    while (node->left != nullptr) node = node->left;
    return node;
}

Node* delete_node(Node* root, int key) {
    if (root == nullptr) return nullptr;
    if (key < root->val) {
        root->left = delete_node(root->left, key);
    } else if (key > root->val) {
        root->right = delete_node(root->right, key);
    } else {
        // Found the node to remove.
        if (root->left == nullptr) return root->right;   // leaf or right child only
        if (root->right == nullptr) return root->left;   // left child only
        Node* succ = min_value_node(root->right);
        root->val = succ->val;                           // copy value up...
        root->right = delete_node(root->right, succ->val); // ...delete it below
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
    for (int v : values) root = insert(root, v);
    int keys[] = {20, 30, 70};  // leaf, one child, two children
    for (int key : keys) {
        root = delete_node(root, key);
        cout << "deleted " << key << " -> ";
        inorder(root);
        cout << endl;
    }
    return 0;
}
