# sortsort · Multi-Constraint Digit DP
# Category: Dynamic Programming
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-constraint-digit-dp

def count_numbers(L, R):
    def solve(n):
        if n < 0: return 0
        digits = [int(d) for d in str(n)]
        length = len(digits)
        memo = {}
        def dp(pos, tight, sum_parity, last, started):
            if pos == length:
                return 1 if started and sum_parity == 0 else 0
            key = (pos, tight, sum_parity, last, started)
            if key in memo: return memo[key]
            limit = digits[pos] if tight else 9
            result = 0
            for d in range(0, limit + 1):
                ntight = tight and (d == limit)
                nstarted = started or d > 0
                nparity = sum_parity
                nlast = last
                if nstarted:
                    nparity = (sum_parity + d) % 2
                    if last != -1 and d == last:
                        continue
                    nlast = d
                result += dp(pos + 1, ntight, nparity, nlast, nstarted)
            memo[key] = result
            return result
        return dp(0, True, 0, -1, False)
    return solve(R) - solve(L - 1)

print(count_numbers(1, 100))
