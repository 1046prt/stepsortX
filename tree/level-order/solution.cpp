// Stepsort · Level-order Traversal
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/level-order

// Level-order traversal (BFS) printing the tree level by level
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

void level_order(Node* root) {
    // BFS with a queue; each outer pass drains exactly one level.
    if (root == nullptr) return;
    queue<Node*> q;
    q.push(root);
    int depth = 0;
    while (!q.empty()) {
        ++depth;
        int n = q.size();
        cout << "level " << depth << " :";
        for (int i = 0; i < n; ++i) {
            Node* node = q.front();
            q.pop();
            cout << " " << node->val;
            if (node->left != nullptr) q.push(node->left);
            if (node->right != nullptr) q.push(node->right);
        }
        cout << endl;
    }
}

int main() {
    Node* root = nullptr;
    for (int v : {50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);
    level_order(root);
    return 0;
}
