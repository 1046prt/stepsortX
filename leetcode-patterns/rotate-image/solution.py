# Stepsort · Rotate Image
# Category: Arrays & Stacks
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rotate-image

def rotate(matrix):
    n = len(matrix)
    # Transpose
    for i in range(n):
        for j in range(i + 1, n):
            matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
    # Reverse each row
    for row in matrix:
        row.reverse()

if __name__ == "__main__":
    m = [[1,2,3],[4,5,6],[7,8,9]]
    rotate(m)
    for row in m:
        print(row)
