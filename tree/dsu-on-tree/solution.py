# sortsort · DSU on Tree (Small-to-Large)
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dsu-on-tree

from collections import defaultdict

parent = [-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7]
color = [1, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1]
n = len(parent)
children = defaultdict(list)
root = 0
for v in range(n):
    if parent[v] == -1:
        root = v
    else:
        children[parent[v]].append(v)

size = [1] * n
postorder = []
stack = [(root, False)]
while stack:
    v, processed = stack.pop()
    if processed:
        postorder.append(v)
        for c in children[v]:
            size[v] += size[c]
    else:
        stack.append((v, True))
        for c in children[v]:
            stack.append((c, False))

maps = [None] * n
answer = [0] * n
for v in postorder:
    big = -1
    for c in children[v]:
        if big == -1 or size[c] > size[big]:
            big = c
    if big == -1:
        maps[v] = {color[v]}
    else:
        cur = maps[big]
        maps[big] = None
        cur.add(color[v])
        for c in children[v]:
            if c != big:
                cur |= maps[c]
                maps[c] = None
        maps[v] = cur
    answer[v] = len(maps[v])

print("distinct colors per subtree:", answer)
