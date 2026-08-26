# Stepsort · Insertion
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-insertion

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def insert_at_head(head, val):
    node = Node(val)
    node.next = head
    return node


def insert_at_tail(head, val):
    node = Node(val)
    if head is None:
        return node
    curr = head
    while curr.next is not None:
        curr = curr.next
    curr.next = node
    return head


def insert_at_position(head, pos, val):
    if pos <= 0:
        return insert_at_head(head, val)
    curr = head
    for _ in range(pos - 1):
        if curr is None:
            return head  # position out of range: ignore
        curr = curr.next
    if curr is None:
        return head
    node = Node(val)
    node.next = curr.next
    curr.next = node
    return head


def to_string(head):
    parts = []
    while head is not None:
        parts.append(str(head.val))
        head = head.next
    return " -> ".join(parts) if parts else "(empty)"


if __name__ == "__main__":
    head = None
    head = insert_at_head(head, 3)
    print("insert 3 at head:", to_string(head))
    head = insert_at_head(head, 1)
    print("insert 1 at head:", to_string(head))
    head = insert_at_tail(head, 7)
    print("insert 7 at tail:", to_string(head))
    head = insert_at_position(head, 2, 5)
    print("insert 5 at index 2:", to_string(head))
    head = insert_at_position(head, 0, 0)
    print("insert 0 at index 0:", to_string(head))
