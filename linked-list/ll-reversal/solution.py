# Stepsort · Reversal
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-reversal

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def reverse_list(head):
    # Walk the list flipping each next pointer backwards.
    prev = None
    curr = head
    while curr is not None:
        nxt = curr.next   # save the rest of the list
        curr.next = prev  # flip one pointer
        prev = curr       # advance prev
        curr = nxt        # advance curr
    return prev


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
    head = build([1, 2, 3, 4, 5])
    print("before:", to_string(head))
    head = reverse_list(head)
    print("after: ", to_string(head))
