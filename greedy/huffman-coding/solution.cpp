// sortsort · Huffman Coding
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/huffman-coding

#include <bits/stdc++.h>
using namespace std;

struct Node {
    char ch;
    int freq;
    Node* left;
    Node* right;
    Node(char c, int f) : ch(c), freq(f), left(nullptr), right(nullptr) {}
};

struct Compare {
    bool operator()(Node* a, Node* b) { return a->freq > b->freq; }
};

void collectCodes(Node* node, string prefix, map<char, string>& codes) {
    if (!node->left && !node->right) {
        codes[node->ch] = prefix.empty() ? "0" : prefix;
        return;
    }
    collectCodes(node->left, prefix + "0", codes);
    collectCodes(node->right, prefix + "1", codes);
}

int main() {
    string text = "huffman coding example";
    map<char, int> freq;
    for (char c : text) freq[c]++;

    priority_queue<Node*, vector<Node*>, Compare> pq;
    for (auto& p : freq) pq.push(new Node(p.first, p.second));

    // merge the two least frequent nodes until one tree remains
    while (pq.size() > 1) {
        Node* left = pq.top(); pq.pop();
        Node* right = pq.top(); pq.pop();
        Node* merged = new Node(char(0), left->freq + right->freq);
        merged->left = left;
        merged->right = right;
        pq.push(merged);
    }

    map<char, string> codes;
    collectCodes(pq.top(), "", codes);
    cout << "Huffman codes:" << endl;
    int totalBits = 0;
    for (auto& p : codes) {
        cout << "'" << p.first << "' -> " << p.second << endl;
        totalBits += p.second.size() * freq[p.first];
    }
    cout << "Encoded length in bits: " << totalBits << endl;
    return 0;
}
