# Stepsort · SOS DP (Sum Over Subsets)
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sos-dp

def sos_dp(arr):
    n = len(arr)
    dp = arr[:]
    for i in range(n):
        for mask in range(1 << n):
            if mask & (1 << i):
                dp[mask] += dp[mask ^ (1 << i)]
    return dp

arr = [1, 2, 3, 4]
result = sos_dp(arr)
for mask in range(len(result)):
    print(f"F[{mask:04b}] = {result[mask]}")
