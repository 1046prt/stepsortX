# sortsort · Bloom Filter
# Category: Randomized Algorithms
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-bloom-filter

class BloomFilter:
    # Probable membership via a bit array plus two hash functions.
    # Absent items may rarely test positive (false positive), but
    # present items are never reported absent.

    def __init__(self, size):
        self.size = size
        self.bits = [False] * size

    @staticmethod
    def h1(item, size):
        h = 0
        for ch in item:
            h = (h * 31 + ord(ch)) % size
        return h

    @staticmethod
    def h2(item, size):
        h = 5381
        for ch in item:
            h = (h * 33 + ord(ch)) % size
        return h

    def add(self, item):
        self.bits[self.h1(item, self.size)] = True
        self.bits[self.h2(item, self.size)] = True

    def possibly_contains(self, item):
        return (self.bits[self.h1(item, self.size)]
                and self.bits[self.h2(item, self.size)])


if __name__ == "__main__":
    bf = BloomFilter(64)
    for word in ["apple", "banana", "cherry"]:
        bf.add(word)
    print("apple inserted -> present?", bf.possibly_contains("apple"))
    for word in ["date", "fig", "grape", "kiwi"]:
        if bf.possibly_contains(word):
            print(word, "-> false positive (not inserted)")
        else:
            print(word, "-> definitely absent")
