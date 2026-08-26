// sortsort · Trie Insert & Search
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/trie-insert-search

// Trie insert, search and startsWith for lowercase words

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        static int idx(char ch) { return ch - 'a'; }

        TrieNode walk(String s) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                node = node.children[idx(ch)];
                if (node == null) return null;
            }
            return node;
        }

        void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int i = idx(ch);
                if (node.children[i] == null) node.children[i] = new TrieNode();
                node = node.children[i];
            }
            node.isEnd = true;
        }

        boolean search(String word) {
            // true only if the whole word was inserted
            TrieNode node = walk(word);
            return node != null && node.isEnd;
        }

        boolean startsWith(String prefix) {
            // true if any stored word begins with prefix
            return walk(prefix) != null;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] inserted = {"cat", "car", "card", "dog", "do"};
        for (String w : inserted) trie.insert(w);

        String[] lookups = {"cat", "card", "ca", "dog", "dot"};
        for (String w : lookups) {
            System.out.println(w + " -> search: " + trie.search(w));
        }
        String[] prefixes = {"ca", "do", "de"};
        for (String p : prefixes) {
            System.out.println(p + " -> startsWith: " + trie.startsWith(p));
        }
    }
}
