# Stepsort · Remove Duplicates
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-remove-duplicates

class Node:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def remove_duplicates(head):
    # Sorted input keeps duplicates adjacent, so one pass suffices.
    current = head
    while current and current.next:
        if current.val == current.next.val:
            current.next = current.next.next
        else:
            current = current.next
    return head


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
    head = build([1, 2, 2, 3, 4, 4, 4, 5])
    print("sorted input:")
    print_list(head)
    remove_duplicates(head)
    print("after removing duplicates:")
    print_list(head)
