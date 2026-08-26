// Stepsort · Inorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/inorder

// Inorder traversal of a BST: recursive + iterative (explicit stack)
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

void inorder_recursive(Node* node, vector<int>& out) {
    if (node == nullptr) return;
    inorder_recursive(node->left, out);    // left
    out.push_back(node->val);              // root
    inorder_recursive(node->right, out);   // right
}

vector<int> inorder_iterative(Node* root) {
    vector<int> out;
    vector<Node*> st;
    Node* cur = root;
    while (cur != nullptr || !st.empty()) {
        while (cur != nullptr) {   // slide left, saving nodes on the stack
            st.push_back(cur);
            cur = cur->left;
        }
        cur = st.back();           // visit the node
        st.pop_back();
        out.push_back(cur->val);
        cur = cur->right;          // continue with the right subtree
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
    inorder_recursive(root, rec);
    cout << "recursive: ";
    print_vec(rec);
    cout << "iterative: ";
    print_vec(inorder_iterative(root));
    return 0;
}
