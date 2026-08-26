// sortsort · Tree Sort
// Category: Sorting
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-sort

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int key;
    Node* left;
    Node* right;
    explicit Node(int k) : key(k), left(nullptr), right(nullptr) {}
};

Node* insert(Node* root, int key) {
    if (root == nullptr) return new Node(key);
    if (key < root->key) root->left = insert(root->left, key);
    else root->right = insert(root->right, key);
    return root;
}

void inorder(Node* node, vector<int>& out) {
    if (node == nullptr) return;
    inorder(node->left, out);
    out.push_back(node->key);
    inorder(node->right, out);
}

void destroy(Node* node) {
    if (node == nullptr) return;
    destroy(node->left);
    destroy(node->right);
    delete node;
}

vector<int> treeSort(const vector<int>& arr) {
    Node* root = nullptr;
    for (int key : arr) root = insert(root, key);
    vector<int> sorted;
    inorder(root, sorted);
    destroy(root);
    return sorted;
}

int main() {
    vector<int> data = {7, 2, 9, 1, 5, 5, 3};
    cout << "sorted:";
    for (int x : treeSort(data)) cout << " " << x;
    cout << endl;
    return 0;
}
