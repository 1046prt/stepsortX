# Stepsort · Sort Linked List
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-sort

class Node:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def split_middle(head):
    # Slow/fast pointers: slow stops at the end of the first half.
    slow = head
    fast = head.next
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow


def merge(a, b):
    dummy = Node()
    tail = dummy
    while a and b:
        if a.val <= b.val:
            tail.next = a
            a = a.next
        else:
            tail.next = b
            b = b.next
        tail = tail.next
    tail.next = a if a else b
    return dummy.next


def sort_list(head):
    if not head or not head.next:
        return head
    mid = split_middle(head)
    right_half = mid.next
    mid.next = None
    left = sort_list(head)
    right = sort_list(right_half)
    return merge(left, right)


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
    head = build([5, 3, 8, 1, 9, 2, 7])
    print_list(sort_list(head))
