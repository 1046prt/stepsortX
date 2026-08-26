# sortsort · Add Two Numbers
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-add-numbers

class Node:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def add_two_numbers(l1, l2):
    # Digits are least significant first, so add pairwise with carry.
    dummy = Node()
    tail = dummy
    carry = 0
    while l1 or l2 or carry:
        total = carry
        if l1:
            total += l1.val
            l1 = l1.next
        if l2:
            total += l2.val
            l2 = l2.next
        carry = total // 10
        tail.next = Node(total % 10)
        tail = tail.next
    return dummy.next


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
    a = build([2, 4, 3])      # 342
    b = build([5, 6, 4])      # 465
    print_list(add_two_numbers(a, b))

    c = build([9, 9, 9, 9])   # 9999
    d = build([1])            # 1
    print_list(add_two_numbers(c, d))
