# sortsort · Double Hashing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-double-hashing

CAP = 11  # prime so any nonzero stride eventually visits every slot


def h1(key):
    return key % CAP


def h2(key):
    return 1 + (key % (CAP - 1))  # stride, never zero


def insert(keys, values, key, value):
    base = h1(key)
    stride = h2(key)
    for i in range(CAP):
        slot = (base + i * stride) % CAP
        if keys[slot] is None or keys[slot] == key:
            keys[slot] = key
            values[slot] = value
            return i
    return -1


def search(keys, key):
    base = h1(key)
    stride = h2(key)
    for i in range(CAP):
        slot = (base + i * stride) % CAP
        if keys[slot] is None:
            return -1
        if keys[slot] == key:
            return slot
    return -1


if __name__ == "__main__":
    keys = [None] * CAP
    values = [None] * CAP
    for k, v in ((10, "A"), (21, "B"), (32, "C")):
        # same home slot 10, but different strides avoid clustering
        attempts = insert(keys, values, k, v)
        print("insert", k, "-> stride", h2(k), ", attempts", attempts + 1)
    for k in (10, 21, 32, 54):
        print("search", k, "-> slot", search(keys, k))
