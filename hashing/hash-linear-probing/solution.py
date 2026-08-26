# sortsort · Linear Probing
# Category: Hashing
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-linear-probing

CAP = 11
EMPTY = None
DELETED = -1  # tombstone marking a vacated slot


def h(key):
    return key % CAP


def insert(keys, values, key, value):
    first_dead = -1
    for step in range(CAP):
        slot = (h(key) + step) % CAP
        if keys[slot] is EMPTY:
            target = first_dead if first_dead != -1 else slot
            keys[target] = key
            values[target] = value
            return step + 1  # probes used
        if keys[slot] == DELETED:
            if first_dead == -1:
                first_dead = slot  # remember earliest tombstone
        elif keys[slot] == key:
            values[slot] = value
            return step + 1
    return -1  # table full


def search(keys, values, key):
    for step in range(CAP):
        slot = (h(key) + step) % CAP
        if keys[slot] is EMPTY:
            return None  # empty gap: key cannot exist past it
        if keys[slot] != DELETED and keys[slot] == key:
            return values[slot]
    return None


def delete(keys, key):
    for step in range(CAP):
        slot = (h(key) + step) % CAP
        if keys[slot] is EMPTY:
            return False
        if keys[slot] == key:
            keys[slot] = DELETED
            return True
    return False


if __name__ == "__main__":
    keys = [EMPTY] * CAP
    values = [EMPTY] * CAP
    for k, v in ((22, "V"), (33, "G"), (44, "S")):  # all hash to slot 0
        print("insert", k, "->", insert(keys, values, k, v), "probes")
    print("search 33 ->", search(keys, values, 33))
    print("delete 33 ->", delete(keys, 33))
    print("search 33 after delete ->", search(keys, values, 33))
    print("insert 55 ->", insert(keys, values, 55, "F"),
          "probes (reused tombstone)")
    print("final slots:", [(i, keys[i]) for i in range(CAP)])
