# Stepsort · Daily Temperatures
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/daily-temperatures

def daily_temperatures(temps):
    answer = [0] * len(temps)
    stack = []  # indices still waiting for a warmer day
    for i, temp in enumerate(temps):
        while stack and temps[stack[-1]] < temp:
            j = stack.pop()
            answer[j] = i - j
        stack.append(i)
    return answer


if __name__ == "__main__":
    print(daily_temperatures([73, 74, 75, 71, 69, 72, 76, 73]))
    print(daily_temperatures([30, 40, 50, 60]))
    print(daily_temperatures([5, 4, 3, 2]))
