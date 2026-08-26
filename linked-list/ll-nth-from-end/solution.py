# sortsort · Nth from End
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-nth-from-end

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def nth_from_end(head, n):
    # Advance first n steps, then move both pointers together;
    # when first leaves the list, second sits n from the end.
    first = head
    for _ in range(n):
        if first is None:
            return None  # n exceeds the list length
        first = first.next
    second = head
    while first is not None:
        first = first.next
        second = second.next
    return second


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


if __name__ == "__main__":
    head = build([10, 20, 30, 40, 50])
    for n in (1, 3, 5, 6):
        node = nth_from_end(head, n)
        if node is not None:
            print(str(n) + "-th from end:", node.val)
        else:
            print(str(n) + "-th from end: not found")
