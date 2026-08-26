# Stepsort · Quadratic Probing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-quadratic-probing

CAP = 11  # prime capacity keeps the probe sequence well spread


def h(key):
    return key % CAP


def insert(keys, values, key, value):
    for i in range(CAP):
        slot = (h(key) + i * i) % CAP  # offsets 0, 1, 4, 9, ...
        if keys[slot] is None:
            keys[slot] = key
            values[slot] = value
            return i + 1
        if keys[slot] == key:
            values[slot] = value
            return i + 1
    return -1  # sequence exhausted: load factor too high


def search(keys, key):
    for i in range(CAP):
        slot = (h(key) + i * i) % CAP
        if keys[slot] is None:
            return -1
        if keys[slot] == key:
            return slot
    return -1


if __name__ == "__main__":
    keys = [None] * CAP
    values = [None] * CAP
    for k, v in ((10, "A"), (21, "B"), (32, "C"), (43, "D")):
        # all four hash to slot 10; squares push them apart
        print("insert", k, "->", insert(keys, values, k, v), "probes")
    print("table:", [(i, keys[i]) for i in range(CAP) if keys[i] is not None])
    for k in (32, 54):
        slot = search(keys, k)
        if slot != -1:
            print("search", k, "-> slot", slot, values[slot])
        else:
            print("search", k, "-> absent (-1)")
