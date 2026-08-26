# Stepsort · Knight's Tour
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/knight-tour

N = 5
moves = [(2, 1), (1, 2), (-1, 2), (-2, 1), (-2, -1), (-1, -2), (1, -2), (2, -1)]


def degree(board, r, c):
    # Warnsdorff heuristic: count onward moves for ordering
    count = 0
    for dr, dc in moves:
        nr, nc = r + dr, c + dc
        if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 0:
            count += 1
    return count


def tour(board, r, c, step):
    if step == N * N:
        return True
    candidates = []
    for dr, dc in moves:
        nr, nc = r + dr, c + dc
        if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 0:
            candidates.append((degree(board, nr, nc), nr, nc))
    candidates.sort()
    for _, nr, nc in candidates:
        board[nr][nc] = step + 1
        if tour(board, nr, nc, step + 1):
            return True
        board[nr][nc] = 0
    return False


if __name__ == "__main__":
    board = [[0] * N for _ in range(N)]
    board[0][0] = 1
    if tour(board, 0, 0, 1):
        for row in board:
            print(" ".join(str(v).rjust(2) for v in row))
    else:
        print("No tour found")
