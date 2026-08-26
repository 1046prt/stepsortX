# sortsort · Strassen's Multiplication
# Category: Divide & Conquer
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/strassen

def add_matrix(A, B):
    n = len(A)
    return [[A[i][j] + B[i][j] for j in range(n)] for i in range(n)]


def sub_matrix(A, B):
    n = len(A)
    return [[A[i][j] - B[i][j] for j in range(n)] for i in range(n)]


def split(M):
    h = len(M) // 2
    top_left = [row[:h] for row in M[:h]]
    top_right = [row[h:] for row in M[:h]]
    bottom_left = [row[:h] for row in M[h:]]
    bottom_right = [row[h:] for row in M[h:]]
    return top_left, top_right, bottom_left, bottom_right


def join(c11, c12, c21, c22):
    top = [r1 + r2 for r1, r2 in zip(c11, c12)]
    bottom = [r1 + r2 for r1, r2 in zip(c21, c22)]
    return top + bottom


def strassen(A, B):
    n = len(A)
    if n <= 2:
        return [[sum(A[i][k] * B[k][j] for k in range(n)) for j in range(n)]
                for i in range(n)]
    a11, a12, a21, a22 = split(A)
    b11, b12, b21, b22 = split(B)
    m1 = strassen(add_matrix(a11, a22), add_matrix(b11, b22))
    m2 = strassen(add_matrix(a21, a22), b11)
    m3 = strassen(a11, sub_matrix(b12, b22))
    m4 = strassen(a22, sub_matrix(b21, b11))
    m5 = strassen(add_matrix(a11, a12), b22)
    m6 = strassen(sub_matrix(a21, a11), add_matrix(b11, b12))
    m7 = strassen(sub_matrix(a12, a22), add_matrix(b21, b22))
    c11 = add_matrix(sub_matrix(add_matrix(m1, m4), m5), m7)
    c12 = add_matrix(m3, m5)
    c21 = add_matrix(m2, m4)
    c22 = add_matrix(sub_matrix(add_matrix(m1, m3), m2), m6)
    return join(c11, c12, c21, c22)


if __name__ == "__main__":
    A = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    B = [[17, 18, 19, 20], [21, 22, 23, 24], [25, 26, 27, 28], [29, 30, 31, 32]]
    print("A x B:")
    for row in strassen(A, B):
        print(row)
