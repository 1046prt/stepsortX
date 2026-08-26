// sortsort · Postorder Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/postorder

// Postorder traversal of a BST (left, right, root):
// recursive + iterative with two stacks.
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

void postorder_recursive(Node* node, vector<int>& out) {
    if (node == nullptr) return;
    postorder_recursive(node->left, out);   // left
    postorder_recursive(node->right, out);  // right
    out.push_back(node->val);               // root
}

vector<int> postorder_iterative(Node* root) {
    // s1 emits nodes in reversed postorder; s2 reverses that order.
    vector<int> out;
    vector<Node*> s1, s2;
    if (root != nullptr) s1.push_back(root);
    while (!s1.empty()) {
        Node* node = s1.back();
        s1.pop_back();
        s2.push_back(node);
        if (node->left != nullptr) s1.push_back(node->left);
        if (node->right != nullptr) s1.push_back(node->right);
    }
    while (!s2.empty()) {
        out.push_back(s2.back()->val);
        s2.pop_back();
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
    postorder_recursive(root, rec);
    cout << "recursive: ";
    print_vec(rec);
    cout << "iterative: ";
    print_vec(postorder_iterative(root));
    return 0;
}
