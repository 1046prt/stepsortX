# Stepsort · Subset Convolution
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-convolution

def subset_convolution(a, b):
    n = len(a)
    bits = n.bit_length() - 1
    fa = [[0] * n for _ in range(bits + 1)]
    fb = [[0] * n for _ in range(bits + 1)]
    for mask in range(n):
        pc = bin(mask).count("1")
        fa[pc][mask] = a[mask]
        fb[pc][mask] = b[mask]
    for k in range(bits + 1):
        for i in range(bits):
            for mask in range(n):
                if mask & (1 << i):
                    fa[k][mask] += fa[k][mask ^ (1 << i)]
                    fb[k][mask] += fb[k][mask ^ (1 << i)]
    fc = [[0] * n for _ in range(bits + 1)]
    for mask in range(n):
        for i in range(bits + 1):
            for j in range(bits + 1 - i):
                fc[i + j][mask] += fa[i][mask] * fb[j][mask]
    for k in range(bits + 1):
        for i in range(bits):
            for mask in range(n):
                if mask & (1 << i):
                    fc[k][mask] -= fc[k][mask ^ (1 << i)]
    return fc[bits]

a = [1, 2, 0, 1]
b = [0, 1, 1, 0]
print("Subset convolution:", subset_convolution(a, b))
