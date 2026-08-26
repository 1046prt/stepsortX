# Stepsort · Sudoku Solver
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sudoku-solver

grid = [
    [5, 3, 0, 0, 7, 0, 0, 0, 0],
    [6, 0, 0, 1, 9, 5, 0, 0, 0],
    [0, 9, 8, 0, 0, 0, 0, 6, 0],
    [8, 0, 0, 0, 6, 0, 0, 0, 3],
    [4, 0, 0, 8, 0, 3, 0, 0, 1],
    [7, 0, 0, 0, 2, 0, 0, 0, 6],
    [0, 6, 0, 0, 0, 0, 2, 8, 0],
    [0, 0, 0, 4, 1, 9, 0, 0, 5],
    [0, 0, 0, 0, 8, 0, 0, 7, 9],
]


def valid(r, c, v):
    for i in range(9):
        if grid[r][i] == v or grid[i][c] == v:
            return False
    br, bc = 3 * (r // 3), 3 * (c // 3)
    for i in range(br, br + 3):
        for j in range(bc, bc + 3):
            if grid[i][j] == v:
                return False
    return True


def solve():
    for r in range(9):
        for c in range(9):
            if grid[r][c] == 0:
                for v in range(1, 10):
                    if valid(r, c, v):
                        grid[r][c] = v
                        if solve():
                            return True
                        grid[r][c] = 0
                return False
    return True


if __name__ == "__main__":
    if solve():
        for row in grid:
            print(" ".join(str(x) for x in row))
    else:
        print("No solution exists")
