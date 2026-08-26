# sortsort · Suffix Automaton
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton

class State:
    __slots__ = ("len", "link", "next")

    def __init__(self, length, link=-1):
        self.len = length
        self.link = link
        self.next = {}


class SuffixAutomaton:
    def __init__(self, s):
        self.st = [State(0)]
        self.last = 0
        for ch in s:
            self.extend(ch)

    def extend(self, ch):
        cur = len(self.st)
        self.st.append(State(self.st[self.last].len + 1))
        p = self.last
        while p != -1 and ch not in self.st[p].next:
            self.st[p].next[ch] = cur
            p = self.st[p].link
        if p == -1:
            self.st[cur].link = 0
        else:
            q = self.st[p].next[ch]
            if self.st[q].len == self.st[p].len + 1:
                self.st[cur].link = q
            else:
                clone = len(self.st)
                self.st.append(State(self.st[p].len + 1, self.st[q].link))
                self.st[clone].next = self.st[q].next.copy()
                while p != -1 and self.st[p].next.get(ch) == q:
                    self.st[p].next[ch] = clone
                    p = self.st[p].link
                self.st[q].link = clone
                self.st[cur].link = clone
        self.last = cur

    def count_distinct_substrings(self):
        return sum(s.len - self.st[s.link].len for s in self.st[1:])


if __name__ == "__main__":
    sam = SuffixAutomaton("abab")
    print(f"states: {len(sam.st)}")                       # 6
    print(f"distinct substrings: {sam.count_distinct_substrings()}")  # 7
