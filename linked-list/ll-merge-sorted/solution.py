# Stepsort · Merge Two Sorted Lists
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-merge-sorted

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def merge_sorted_lists(l1, l2):
    # Attach the smaller front node to a dummy tail each round.
    dummy = Node(0)
    tail = dummy
    while l1 is not None and l2 is not None:
        if l1.val <= l2.val:
            tail.next = l1
            l1 = l1.next
        else:
            tail.next = l2
            l2 = l2.next
        tail = tail.next
    tail.next = l1 if l1 is not None else l2
    return dummy.next


def build(values):
    head = None
    tail = None
    for v in values:
        node = Node(v)
        if head is None:
            head = tail = node
        else:
            tail.next = node
            tail = node
    return head


def to_string(head):
    parts = []
    while head is not None:
        parts.append(str(head.val))
        head = head.next
    return " -> ".join(parts) if parts else "(empty)"


if __name__ == "__main__":
    a = build([1, 3, 5, 7])
    b = build([2, 4, 6, 8])
    merged = merge_sorted_lists(a, b)
    print("list a:", to_string(a))
    print("list b:", to_string(b))
    print("merged:", to_string(merged))
