# sortsort · Palindrome Check
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-palindrome

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def reverse_list(head):
    prev = None
    curr = head
    while curr is not None:
        nxt = curr.next
        curr.next = prev
        prev = curr
        curr = nxt
    return prev


def is_palindrome(head):
    # Step 1: find the middle with slow/fast pointers.
    if head is None or head.next is None:
        return True
    slow = fast = head
    while fast.next is not None and fast.next.next is not None:
        slow = slow.next
        fast = fast.next.next

    # Step 2: reverse the second half.
    second = reverse_list(slow.next)

    # Step 3: compare the two halves.
    p1, p2 = head, second
    while p2 is not None:
        if p1.val != p2.val:
            return False
        p1 = p1.next
        p2 = p2.next
    return True


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
    tests = [
        [1, 2, 3, 2, 1],
        [1, 2, 2, 1],
        [1, 2, 3],
        [],
    ]
    for values in tests:
        print(values, "is palindrome:", is_palindrome(build(values)))
