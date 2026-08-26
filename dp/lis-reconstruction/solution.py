# sortsort · LIS Reconstruction
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis-reconstruction

def lis_with_reconstruction(arr):
    from bisect import bisect_left
    n = len(arr)
    tails = []
    tail_idx = []
    prev = [-1] * n
    dp = [0] * n
    for i in range(n):
        pos = bisect_left(tails, arr[i])
        if pos == len(tails):
            tails.append(arr[i])
            tail_idx.append(i)
        else:
            tails[pos] = arr[i]
            tail_idx[pos] = i
        dp[i] = pos + 1
        prev[i] = tail_idx[pos - 1] if pos > 0 else -1
    length = len(tails)
    lis = []
    k = tail_idx[length - 1]
    while k != -1:
        lis.append(arr[k])
        k = prev[k]
    lis.reverse()
    return length, lis

arr = [3, 1, 4, 1, 5, 9, 2, 6]
length, lis = lis_with_reconstruction(arr)
print(f"LIS length: {length}, LIS: {lis}")
