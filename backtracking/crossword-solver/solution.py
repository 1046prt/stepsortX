# sortsort · Crossword Solver
# Category: Backtracking
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/crossword-solver

pattern = [
    "--+----",
    "-----+",
]
words = ["hi", "world", "code"]


def find_slots(pattern):
    slots = []  # each slot: (row, start_col, length)
    for r, line in enumerate(pattern):
        c = 0
        while c < len(line):
            if line[c] == "-":
                start = c
                while c < len(line) and line[c] == "-":
                    c += 1
                slots.append((r, start, c - start))
            else:
                c += 1
    return slots


def solve(grid, slots, idx, used):
    if idx == len(slots):
        return True
    r, start, length = slots[idx]
    for wi, word in enumerate(words):
        if not used[wi] and len(word) == length:
            used[wi] = True
            for j, ch in enumerate(word):
                grid[r][start + j] = ch
            if solve(grid, slots, idx + 1, used):
                return True
            for j in range(length):  # undo placement
                grid[r][start + j] = "-"
            used[wi] = False
    return False


if __name__ == "__main__":
    grid = [list(row) for row in pattern]
    slots = find_slots(pattern)
    if solve(grid, slots, 0, [False] * len(words)):
        for row in grid:
            print("".join(row))
    else:
        print("No solution")
