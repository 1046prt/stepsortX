// Stepsort · Suffix Tree (Compressed)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-tree-construction

#include <bits/stdc++.h>
using namespace std;

struct Node {
    map<char, Node*> children;
    bool isLeaf = false;
};

int main() {
    string s = "banana";
    Node* root = new Node();
    int nodeCount = 1, leafCount = 0;

    for (size_t k = 1; k <= s.size(); k++) {
        string suffix = s.substr(s.size() - k);
        Node* cur = root;
        for (char c : suffix) {
            if (!cur->children.count(c)) {
                cur->children[c] = new Node();
                nodeCount++;
            }
            cur = cur->children[c];
        }
        if (!cur->isLeaf) {
            cur->isLeaf = true;
            leafCount++;
        }
    }
    cout << "nodes: " << nodeCount << ", leaves: " << leafCount << endl;
}
