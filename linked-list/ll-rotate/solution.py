# Stepsort · Rotate List
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-rotate

class Node:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def rotate_right(head, k):
    # Reduce k modulo the length, then cut the last k nodes off
    # and splice them onto the front.
    if not head or not head.next or k == 0:
        return head
    length = 1
    tail = head
    while tail.next:
        tail = tail.next
        length += 1
    k %= length
    if k == 0:
        return head
    new_tail = head
    for _ in range(length - k - 1):
        new_tail = new_tail.next
    new_head = new_tail.next
    new_tail.next = None
    tail.next = head
    return new_head


def build(values):
    dummy = Node()
    tail = dummy
    for v in values:
        tail.next = Node(v)
        tail = tail.next
    return dummy.next


def print_list(head):
    parts = []
    while head:
        parts.append(str(head.val))
        head = head.next
    print(" -> ".join(parts))


if __name__ == "__main__":
    head = build([1, 2, 3, 4, 5])
    head = rotate_right(head, 2)
    print_list(head)

    head = rotate_right(head, 12)
    print_list(head)
