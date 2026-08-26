# sortsort · Rat in a Maze
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rat-in-maze

maze = [
    [1, 0, 0, 0],
    [1, 1, 0, 1],
    [0, 1, 0, 0],
    [0, 1, 1, 1],
]
N = 4


def solve():
    path = [[0] * N for _ in range(N)]

    def go(r, c):
        if r == N - 1 and c == N - 1 and maze[r][c] == 1:
            path[r][c] = 1
            return True
        if 0 <= r < N and 0 <= c < N and maze[r][c] == 1 and path[r][c] == 0:
            path[r][c] = 1
            if go(r + 1, c) or go(r, c + 1) or go(r - 1, c) or go(r, c - 1):
                return True
            path[r][c] = 0
        return False

    return path if go(0, 0) else None


if __name__ == "__main__":
    result = solve()
    if result:
        for row in result:
            print(" ".join(str(x) for x in row))
    else:
        print("No path found")
