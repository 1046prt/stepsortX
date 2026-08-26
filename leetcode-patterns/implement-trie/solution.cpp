// sortsort · Implement Trie (Prefix Tree)
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/implement-trie

#include <bits/stdc++.h>
using namespace std;

struct TrieNode {
    TrieNode* children[26];
    bool isEnd;
    TrieNode() : children{}, isEnd(false) {}
};

class Trie {
public:
    Trie() : root(new TrieNode()) {}

    void insert(const string& word) {
        TrieNode* node = root;
        for (char ch : word) {
            int idx = ch - 'a';
            if (!node->children[idx]) node->children[idx] = new TrieNode();
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(const string& word) {
        TrieNode* node = find(word);
        return node && node->isEnd;
    }

    bool startsWith(const string& prefix) {
        return find(prefix) != nullptr;
    }

private:
    TrieNode* root;

    TrieNode* find(const string& s) {
        TrieNode* node = root;
        for (char ch : s) {
            node = node->children[ch - 'a'];
            if (!node) return nullptr;
        }
        return node;
    }
};

int main() {
    Trie trie;
    trie.insert("apple");
    cout << boolalpha << trie.search("apple") << endl;
    cout << boolalpha << trie.search("app") << endl;
    cout << boolalpha << trie.startsWith("app") << endl;
    trie.insert("app");
    cout << boolalpha << trie.search("app") << endl;
    return 0;
}
