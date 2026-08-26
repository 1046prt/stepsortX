# Stepsort · Spiral Matrix
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/spiral-matrix

def spiral_order(matrix):
    result = []
    top, bottom = 0, len(matrix) - 1
    left, right = 0, len(matrix[0]) - 1
    while top <= bottom and left <= right:
        for c in range(left, right + 1):  # top row, left to right
            result.append(matrix[top][c])
        top += 1
        for r in range(top, bottom + 1):  # right column, top to bottom
            result.append(matrix[r][right])
        right -= 1
        if top <= bottom:
            for c in range(right, left - 1, -1):  # bottom row, right to left
                result.append(matrix[bottom][c])
            bottom -= 1
        if left <= right:
            for r in range(bottom, top - 1, -1):  # left column, bottom to top
                result.append(matrix[r][left])
            left += 1
    return result


if __name__ == "__main__":
    matrix = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9],
    ]
    print(spiral_order(matrix))
