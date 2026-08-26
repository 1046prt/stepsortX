# Stepsort · Cycle Detection
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-cycle-detection

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def has_cycle(head):
    # Slow moves 1 step, fast moves 2 steps.
    # They meet iff the list contains a cycle.
    slow = fast = head
    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            return True
    return False


def find_cycle_start(head):
    # After the pointers meet, restart one at the head;
    # advancing both 1 step meets again at the cycle entry.
    slow = fast = head
    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            slow = head
            while slow is not fast:
                slow = slow.next
                fast = fast.next
            return slow
    return None


if __name__ == "__main__":
    a = Node(1)
    b = Node(2)
    c = Node(3)
    d = Node(4)
    e = Node(5)
    a.next = b
    b.next = c
    c.next = d
    d.next = e

    print("plain list has cycle:", has_cycle(a))

    e.next = b  # tail links back to value 2
    print("linked tail has cycle:", has_cycle(a))
    start = find_cycle_start(a)
    print("cycle starts at value:", start.val if start else None)

    e.next = None  # break the cycle again
    print("after breaking, has cycle:", has_cycle(a))
