# Stepsort · Huffman Coding
# Category: Greedy
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/huffman-coding

import heapq


class Node:
    def __init__(self, char=None, freq=0):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq


def build_codes(text):
    freq = {}
    for ch in text:
        freq[ch] = freq.get(ch, 0) + 1
    heap = [Node(ch, f) for ch, f in freq.items()]
    heapq.heapify(heap)

    # merge the two least frequent nodes until one tree remains
    while len(heap) > 1:
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)
        merged = Node(freq=left.freq + right.freq)
        merged.left = left
        merged.right = right
        heapq.heappush(heap, merged)

    codes = {}

    def walk(node, prefix):
        if node.char is not None:
            codes[node.char] = prefix or "0"
            return
        walk(node.left, prefix + "0")
        walk(node.right, prefix + "1")

    walk(heap[0], "")
    return codes


if __name__ == "__main__":
    text = "huffman coding example"
    codes = build_codes(text)
    for ch, code in sorted(codes.items()):
        print(repr(ch), "->", code)
    encoded_length = sum(len(codes[ch]) for ch in text)
    print("Encoded length in bits:", encoded_length)
