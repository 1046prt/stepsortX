// Stepsort · Lowest Common Ancestor
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lca

#include <bits/stdc++.h>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left, *right;
    explicit TreeNode(int v) : val(v), left(nullptr), right(nullptr) {}
};

TreeNode* buildBST(const vector<int>& values) {
    TreeNode* root = nullptr;
    for (int v : values) {
        if (!root) { root = new TreeNode(v); continue; }
        TreeNode* cur = root;
        while (true) {
            if (v < cur->val) {
                if (!cur->left) { cur->left = new TreeNode(v); break; }
                cur = cur->left;
            } else {
                if (!cur->right) { cur->right = new TreeNode(v); break; }
                cur = cur->right;
            }
        }
    }
    return root;
}

// BST version: value comparison decides the branch to follow.
TreeNode* lcaBST(TreeNode* root, int p, int q) {
    TreeNode* cur = root;
    while (cur) {
        if (p < cur->val && q < cur->val) cur = cur->left;
        else if (p > cur->val && q > cur->val) cur = cur->right;
        else return cur;   // split point
    }
    return nullptr;
}

// General binary tree version: post-order recursion.
TreeNode* lcaGeneral(TreeNode* root, int p, int q) {
    if (!root || root->val == p || root->val == q) return root;
    TreeNode* L = lcaGeneral(root->left, p, q);
    TreeNode* R = lcaGeneral(root->right, p, q);
    if (L && R) return root;
    return L ? L : R;
}

int main() {
    TreeNode* root = buildBST({20, 8, 22, 4, 12, 10, 14});
    int pairs[][2] = {{10, 14}, {14, 8}, {10, 22}};
    for (auto& pr : pairs) {
        int p = pr[0], q = pr[1];
        cout << "LCA(" << p << ", " << q << ") -> BST: "
             << lcaBST(root, p, q)->val
             << " | general: " << lcaGeneral(root, p, q)->val << endl;
    }
    return 0;
}
