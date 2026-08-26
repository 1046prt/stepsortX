// sortsort · Binary Tree Path Sum
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/binary-tree-path-sum

#include <bits/stdc++.h>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int v) : val(v), left(nullptr), right(nullptr) {}
};

bool hasPathSum(TreeNode* node, long long target) {
    // True iff some root-to-leaf path sums exactly to target.
    if (node == nullptr) return false;
    bool leaf = node->left == nullptr && node->right == nullptr;
    if (leaf) return node->val == target;
    long long remaining = target - node->val;
    return hasPathSum(node->left, remaining) || hasPathSum(node->right, remaining);
}

int main() {
    TreeNode* root = new TreeNode(5);
    root->left = new TreeNode(4);
    root->left->left = new TreeNode(11);
    root->left->left->left = new TreeNode(7);
    root->left->left->right = new TreeNode(2);
    root->right = new TreeNode(8);
    root->right->left = new TreeNode(13);
    root->right->right = new TreeNode(4);
    root->right->right->right = new TreeNode(1);
    cout << boolalpha << hasPathSum(root, 22) << " " << hasPathSum(root, 30) << endl;
}
