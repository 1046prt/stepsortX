# sortsort · Copy with Random Pointer
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-copy-random

class Node:
    def __init__(self, val, next=None, random=None):
        self.val = val
        self.next = next
        self.random = random


def copy_random_list(head):
    if not head:
        return None

    # Interleave: insert a clone right after every original node.
    cur = head
    while cur:
        clone = Node(cur.val, cur.next, None)
        cur.next = clone
        cur = clone.next

    # Aim each clone's random at the clone of its target.
    cur = head
    while cur:
        if cur.random:
            cur.next.random = cur.random.next
        cur = cur.next.next

    # Unweave the interleaved original and cloned chains apart.
    dummy = Node(0)
    copy_tail = dummy
    cur = head
    while cur:
        clone = cur.next
        cur.next = clone.next
        copy_tail.next = clone
        copy_tail = clone
        cur = cur.next

    return dummy.next


def print_list(head):
    parts = []
    while head:
        rand_val = str(head.random.val) if head.random else "null"
        parts.append(f"({head.val}, random={rand_val})")
        head = head.next
    print(" -> ".join(parts))


if __name__ == "__main__":
    a = Node(1)
    b = Node(2)
    c = Node(3)
    a.next = b
    b.next = c
    a.random = c
    b.random = a
    c.random = b

    print("original:")
    print_list(a)

    copied = copy_random_list(a)
    print("deep copy:")
    print_list(copied)
