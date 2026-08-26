# Stepsort · Transitive Closure
# Category: Graph
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/transitive-closure

def transitive_closure(n, edges):
    tc = [[0] * n for _ in range(n)]
    for u, v in edges:
        tc[u][v] = 1
    for i in range(n):
        tc[i][i] = 1
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if tc[i][k] and tc[k][j]:
                    tc[i][j] = 1
    return tc

edges = [(0,1),(1,2),(2,3),(3,4),(0,3)]
tc = transitive_closure(5, edges)
for row in tc:
    print(row)
