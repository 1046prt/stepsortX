# sortsort · Flatten Multi-Level List
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-flatten

class Node:
    def __init__(self, val, next=None, down=None):
        self.val = val
        self.next = next
        self.down = down


def flatten(head):
    # Depth-first: finish a whole child chain before visiting its sibling.
    if not head:
        return None
    stack = [head]
    dummy = Node(0)
    tail = dummy
    while stack:
        node = stack.pop()
        tail.next = node
        tail = node
        if node.next:
            stack.append(node.next)
        if node.down:
            stack.append(node.down)
        node.next = None
        node.down = None
    return dummy.next


def print_list(head):
    parts = []
    while head:
        parts.append(str(head.val))
        head = head.next
    print(" -> ".join(parts))


if __name__ == "__main__":
    n1, n2, n3, n4 = Node(1), Node(2), Node(3), Node(4)
    n5, n6, n7, n8 = Node(5), Node(6), Node(7), Node(8)

    n1.next = n2
    n2.next = n3
    n3.next = n4
    n2.down = n5
    n5.down = n6
    n6.down = n7
    n4.down = n8

    print_list(flatten(n1))
