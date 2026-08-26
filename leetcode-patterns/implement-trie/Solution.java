// Stepsort · Implement Trie (Prefix Tree)
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/implement-trie

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        private TrieNode find(String s) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                node = node.children[ch - 'a'];
                if (node == null) return null;
            }
            return node;
        }

        boolean search(String word) {
            TrieNode node = find(word);
            return node != null && node.isEnd;
        }

        boolean startsWith(String prefix) {
            return find(prefix) != null;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
