# Stepsort · Trie Insert & Search
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/trie-insert-search

# Trie insert, search and startsWith for lowercase words

class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    @staticmethod
    def _idx(ch):
        return ord(ch) - ord("a")

    def _walk(self, s):
        node = self.root
        for ch in s:
            node = node.children[self._idx(ch)]
            if node is None:
                return None
        return node

    def insert(self, word):
        node = self.root
        for ch in word:
            i = self._idx(ch)
            if node.children[i] is None:
                node.children[i] = TrieNode()
            node = node.children[i]
        node.is_end = True

    def search(self, word):
        # true only if the whole word was inserted
        node = self._walk(word)
        return node is not None and node.is_end

    def starts_with(self, prefix):
        # true if any stored word begins with prefix
        return self._walk(prefix) is not None


if __name__ == "__main__":
    trie = Trie()
    for w in ["cat", "car", "card", "dog", "do"]:
        trie.insert(w)
    for w in ["cat", "card", "ca", "dog", "dot"]:
        print(w, "-> search:", trie.search(w))
    for p in ["ca", "do", "de"]:
        print(p, "-> startsWith:", trie.starts_with(p))
