# Stepsort · Group Anagrams
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/group-anagrams

def group_anagrams(words):
    groups = {}
    for word in words:
        signature = "".join(sorted(word))  # sorted letters identify family
        groups.setdefault(signature, []).append(word)
    return list(groups.values())


if __name__ == "__main__":
    words = ["eat", "tea", "tan", "ate", "nat", "bat"]
    for i, group in enumerate(group_anagrams(words)):
        print("group", i, "->", group)
