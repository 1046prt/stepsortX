# Stepsort · Deletion
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-deletion

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def delete_by_value(head, target):
    # Remove the first node whose value equals target.
    if head is None:
        return None
    if head.val == target:
        return head.next
    curr = head
    while curr.next is not None and curr.next.val != target:
        curr = curr.next
    if curr.next is not None:
        curr.next = curr.next.next
    return head


def delete_at_position(head, pos):
    # Remove the node at the given 0-based index.
    if head is None:
        return None
    if pos == 0:
        return head.next
    curr = head
    for _ in range(pos - 1):
        curr = curr.next
        if curr is None:
            return head  # position out of range: ignore
    if curr.next is not None:
        curr.next = curr.next.next
    return head


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
    head = build([4, 2, 6, 2, 9])
    print("start:", to_string(head))

    head = delete_by_value(head, 2)
    print("delete value 2:", to_string(head))

    head = delete_by_value(head, 4)
    print("delete value 4:", to_string(head))

    head = delete_at_position(head, 1)
    print("delete index 1:", to_string(head))

    head = delete_at_position(head, 0)
    print("delete index 0:", to_string(head))
