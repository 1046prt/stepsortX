# Stepsort · Eertree (Palindromic Tree)
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eertree

class Eertree:
    def __init__(self):
        self.tree = [{"len": -1, "suff": 0}]
        self.tree.append({"len": 0, "suff": 0})
        self.last = 1

    def extend(self, s, i):
        ch = s[i]
        x = self.last
        while True:
            start = i - self.tree[x]["len"] - 1
            if start >= 0 and s[start] == ch:
                break
            x = self.tree[x]["suff"]
        cand_len = self.tree[x]["len"] + 2

        found = -1
        for t in range(2, len(self.tree)):
            if self.tree[t]["len"] == cand_len:
                sub = s[i - cand_len + 1 : i + 1]
                if sub == sub[::-1]:
                    found = t
                    break

        if found != -1:
            self.last = found
            return f"'{s[i - cand_len + 1:i+1]}' exists"

        suff_len = max(self.tree[x]["len"], 0)
        suff_target = 1
        for t in range(2, len(self.tree)):
            if self.tree[t]["len"] == suff_len:
                suff_target = t
                break
        self.tree.append({"len": cand_len, "suff": suff_target})
        self.last = len(self.tree) - 1
        return f"new palindrome '{s[i - cand_len + 1:i+1]}' (len {cand_len})"


if __name__ == "__main__":
    e = Eertree()
    s = "abba"
    for i in range(len(s)):
        print(e.extend(s, i))
    distinct = len(e.tree) - 2
    print(f"distinct palindromes: {distinct}")   # 4
