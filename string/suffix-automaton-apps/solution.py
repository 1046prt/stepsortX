# sortsort · Suffix Automaton Applications
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton-apps

class SAM:
    def __init__(self, s):
        self.states = [{"len": 0, "link": -1, "next": {}}]
        self.last = 0
        for c in s:
            self._extend(c)

    def _extend(self, c):
        p = self.last
        cur = len(self.states)
        self.states.append({"len": self.states[p]["len"] + 1, "link": 0, "next": {}})
        while p >= 0 and c not in self.states[p]["next"]:
            self.states[p]["next"][c] = cur
            p = self.states[p]["link"]
        if p == -1:
            self.states[cur]["link"] = 0
        else:
            q = self.states[p]["next"][c]
            if self.states[p]["len"] + 1 == self.states[q]["len"]:
                self.states[cur]["link"] = q
            else:
                clone = len(self.states)
                self.states.append({
                    "len": self.states[p]["len"] + 1,
                    "link": self.states[q]["link"],
                    "next": dict(self.states[q]["next"]),
                })
                while p >= 0 and self.states[p]["next"].get(c) == q:
                    self.states[p]["next"][c] = clone
                    p = self.states[p]["link"]
                self.states[q]["link"] = self.states[cur]["link"] = clone
        self.last = cur

    def distinct_substrings(self):
        total = sum(self.states[i]["len"] - self.states[i-1]["len"]
                    for i in range(1, len(self.states)))
        return total

sam = SAM("ababa")
print("Distinct substrings:", sam.distinct_substrings())
