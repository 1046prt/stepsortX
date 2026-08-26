# sortsort · Cartesian Tree
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cartesian-tree

values = [9, 3, 7, 1, 8, 12, 10, 20]
n = len(values)


def build():
    parent = [-1] * n
    left = [-1] * n
    right = [-1] * n
    stack = []
    for i in range(n):
        last = -1
        while stack and values[stack[-1]] > values[i]:
            last = stack.pop()
        if last != -1:
            left[i] = last
            parent[last] = i
        if stack:
            parent[i] = stack[-1]
            right[stack[-1]] = i
        stack.append(i)
    return parent


if __name__ == "__main__":
    parents = build()
    print("parents:", parents)   # expect [1, 3, 1, -1, 3, 6, 4, 6]
