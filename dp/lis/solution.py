# sortsort · Longest Increasing Subsequence
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lis

from bisect import bisect_left


def lis(nums):
    # Patience sorting tails: tails[k] = smallest tail of any increasing
    # subsequence of length k + 1
    tails = []
    tail_idx = []              # index in nums of each tail value
    prev = [-1] * len(nums)    # predecessor index for reconstruction
    for i, x in enumerate(nums):
        pos = bisect_left(tails, x)
        if pos == len(tails):
            tails.append(x)
            tail_idx.append(i)
        else:
            tails[pos] = x
            tail_idx[pos] = i
        prev[i] = tail_idx[pos - 1] if pos > 0 else -1
    # Walk predecessors from the last tail to rebuild one LIS
    seq = []
    k = tail_idx[-1] if tail_idx else -1
    while k != -1:
        seq.append(nums[k])
        k = prev[k]
    seq.reverse()
    return len(tails), seq


if __name__ == "__main__":
    nums = [10, 9, 2, 5, 3, 7, 101, 18]
    length, seq = lis(nums)
    print("LIS length:", length)
    print("One LIS:", seq)
