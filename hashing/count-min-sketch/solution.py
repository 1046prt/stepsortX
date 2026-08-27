# Stepsort · Count-Min Sketch
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-min-sketch

class CountMinSketch:
    def __init__(self, width=8, depth=3):
        self.width = width
        self.depth = depth
        self.table = [[0] * width for _ in range(depth)]
        self.hashes = [
            lambda x, i=i: (x * (i + 1) * 7 + i * 3) % width
            for i in range(depth)
        ]

    def update(self, item, count=1):
        for d in range(self.depth):
            self.table[d][self.hashes[d](item)] += count

    def query(self, item):
        return min(self.table[d][self.hashes[d](item)] for d in range(self.depth))

if __name__ == "__main__":
    cms = CountMinSketch(width=8, depth=3)
    for item in [3, 1, 4, 1, 5, 9, 2, 6]:
        cms.update(item)
    print("estimate for 1:", cms.query(1))  # >= 2
    print("estimate for 9:", cms.query(9))  # >= 1
