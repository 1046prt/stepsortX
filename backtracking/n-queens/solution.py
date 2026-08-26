# Stepsort · N-Queens
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/n-queens

def solve_n_queens(n):
    board = [-1] * n

    def safe(row, col):
        for r in range(row):
            if board[r] == col or abs(board[r] - col) == row - r:
                return False
        return True

    def place(row):
        if row == n:
            return True
        for col in range(n):
            if safe(row, col):
                board[row] = col
                if place(row + 1):
                    return True
                board[row] = -1
        return False

    return board if place(0) else None


if __name__ == "__main__":
    solution = solve_n_queens(4)
    if solution:
        for row in range(4):
            line = ""
            for col in range(4):
                line += "Q " if solution[row] == col else ". "
            print(line)
    else:
        print("No solution")
