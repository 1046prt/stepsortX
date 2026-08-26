// Stepsort · Trie Insert & Search
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/trie-insert-search

// Trie insert, search and startsWith for lowercase words
#include <bits/stdc++.h>
using namespace std;

struct TrieNode {
    TrieNode* children[26];
    bool isEnd;
    TrieNode() : isEnd(false) {
        fill(begin(children), end(children), nullptr);
    }
};

struct Trie {
    TrieNode* root;
    Trie() : root(new TrieNode()) {}

    static int idx(char ch) { return ch - 'a'; }

    TrieNode* walk(const string& s) const {
        TrieNode* node = root;
        for (char ch : s) {
            node = node->children[idx(ch)];
            if (!node) return nullptr;
        }
        return node;
    }

    void insert(const string& word) {
        TrieNode* node = root;
        for (char ch : word) {
            int i = idx(ch);
            if (!node->children[i]) node->children[i] = new TrieNode();
            node = node->children[i];
        }
        node->isEnd = true;
    }

    bool search(const string& word) const {
        // true only if the whole word was inserted
        TrieNode* node = walk(word);
        return node && node->isEnd;
    }

    bool startsWith(const string& prefix) const {
        // true if any stored word begins with prefix
        return walk(prefix) != nullptr;
    }
};

int main() {
    Trie trie;
    vector<string> inserted = {"cat", "car", "card", "dog", "do"};
    for (const string& w : inserted) trie.insert(w);

    cout << boolalpha;
    vector<string> lookups = {"cat", "card", "ca", "dog", "dot"};
    for (const string& w : lookups) {
        cout << w << " -> search: " << trie.search(w) << endl;
    }
    vector<string> prefixes = {"ca", "do", "de"};
    for (const string& p : prefixes) {
        cout << p << " -> startsWith: " << trie.startsWith(p) << endl;
    }
    return 0;
}
