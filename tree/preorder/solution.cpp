// Stepsort · Preorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/preorder

// Preorder traversal of a BST (root, left, right): recursive + iterative
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

void preorder_recursive(Node* node, vector<int>& out) {
    if (node == nullptr) return;
    out.push_back(node->val);              // root
    preorder_recursive(node->left, out);   // left
    preorder_recursive(node->right, out);  // right
}

vector<int> preorder_iterative(Node* root) {
    // Pop, visit, then push right before left so the left side pops first.
    vector<int> out;
    vector<Node*> st;
    if (root != nullptr) st.push_back(root);
    while (!st.empty()) {
        Node* node = st.back();
        st.pop_back();
        out.push_back(node->val);
        if (node->right != nullptr) st.push_back(node->right);
        if (node->left != nullptr) st.push_back(node->left);
    }
    return out;
}

void print_vec(const vector<int>& v) {
    for (size_t i = 0; i < v.size(); ++i) {
        if (i) cout << " ";
        cout << v[i];
    }
    cout << endl;
}

int main() {
    Node* root = nullptr;
    for (int v : {50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);
    vector<int> rec;
    preorder_recursive(root, rec);
    cout << "recursive: ";
    print_vec(rec);
    cout << "iterative: ";
    print_vec(preorder_iterative(root));
    return 0;
}
