# Stepsort · Wavelet Tree
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/wavelet-tree

arr = [3, 1, 4, 1, 5, 2]


def build(seq, lo, hi):
    node = {"lo": lo, "hi": hi, "bound": [0], "left": None, "right": None}
    if lo == hi or not seq:
        return node
    mid = (lo + hi) // 2
    left_seq, right_seq = [], []
    for v in seq:
        if v <= mid:
            left_seq.append(v)
        else:
            right_seq.append(v)
        node["bound"].append(len(left_seq))
    node["left"] = build(left_seq, lo, mid)
    node["right"] = build(right_seq, mid + 1, hi)
    return node


def rank_value(node, c, i):
    while node["lo"] != node["hi"]:
        mid = (node["lo"] + node["hi"]) // 2
        if c <= mid:
            i = node["bound"][i]
            node = node["left"]
        else:
            i -= node["bound"][i]
            node = node["right"]
    return i


if __name__ == "__main__":
    root = build(arr, 1, 5)
    print("rank(1, 6) =", rank_value(root, 1, 6))   # expect 2
    print("rank(5, 6) =", rank_value(root, 5, 6))   # expect 1
