# Stepsort · Consistent Hashing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/consistent-hashing

MASK = 0xFFFFFFFF


def h(text):
    # fold characters, then xor-multiply avalanche for a uniform ring
    value = 0
    for ch in text:
        value = (value * 131 + ord(ch)) & MASK
    value ^= value >> 16
    value = (value * 0x45D9F3B) & MASK
    value ^= value >> 16
    value = (value * 0x45D9F3B) & MASK
    value ^= value >> 16
    return value


class ConsistentHashRing:
    def __init__(self):
        self.entries = []  # sorted list of (hash, server)

    def add_server(self, name):
        self.entries.append((h(name), name))
        self.entries.sort()

    def get_server(self, key):
        kh = h(str(key))
        lo, hi = 0, len(self.entries)  # first entry with hash >= kh
        while lo < hi:
            mid = (lo + hi) // 2
            if self.entries[mid][0] < kh:
                lo = mid + 1
            else:
                hi = mid
        return self.entries[lo % len(self.entries)][1]  # wrap around


if __name__ == "__main__":
    ring = ConsistentHashRing()
    for name in ("alpha", "bravo", "charlie"):
        ring.add_server(name)
    keys = [101, 202, 303, 404, 505, 606]
    before = {}
    print("initial ring:")
    for k in keys:
        before[k] = ring.get_server(k)
        print("  key", k, "->", before[k])
    ring.add_server("delta")
    print("after adding delta:")
    moved = 0
    for k in keys:
        now = ring.get_server(k)
        note = ""
        if now != before[k]:
            note = "(moved)"
            moved += 1
        print("  key", k, "->", now, note)
    print("remapped", moved, "of", len(keys), "keys")
