# sortsort · Palindromic Tree Applications
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/palindromic-tree-apps

class Eertree:
    def __init__(self, s):
        self.tree = [[-1, 0], [0, -1]]
        self.suffix = [1, 0]
        self.length = [0, -1]
        self.count = [0, 0]
        self.pos = [0, 0]
        self.last = 0
        self.size = 2
        for i, c in enumerate(s):
            self._add(c, i)
        for i in range(self.size - 1, 1, -1):
            self.count[self.suffix[i]] += self.count[i]

    def _get_link(self, v, pos):
        while pos - 1 - self.length[v] < 0 or self.s[pos - 1 - self.length[v]] != self.s[pos]:
            v = self.suffix[v]
        return v

    def _add(self, c, pos):
        cur = self._get_link(self.last, pos)
        if not self.tree[cur][ord(c) - 97]:
            self.tree.append([0] * 26)
            self.length.append(self.length[cur] + 2)
            self.suffix.append(0)
            self.count.append(0)
            self.pos.append(pos)
            self.tree[cur][ord(c) - 97] = self.size
            q = self._get_link(self.suffix[cur], pos)
            self.suffix[self.size] = self.tree[q][ord(c) - 97] if self.tree[q][ord(c) - 97] else 1
            self.size += 1
        self.count[self.tree[cur][ord(c) - 97]] += 1
        self.last = self.tree[cur][ord(c) - 97]

tree = Eertree("abacaba")
distinct = tree.size - 2
print("Distinct palindromic substrings:", distinct)
