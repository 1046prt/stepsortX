# Stepsort · Linked List Cycle
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linked-list-cycle

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def has_cycle(head):
    # Floyd's tortoise and hare.
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            return True
    return False


def build(values, pos):
    # pos = index the tail points back to, or -1 for no cycle.
    nodes = [ListNode(v) for v in values]
    for a, b in zip(nodes, nodes[1:]):
        a.next = b
    if nodes and pos != -1:
        nodes[-1].next = nodes[pos]
    return nodes[0] if nodes else None


if __name__ == "__main__":
    print(has_cycle(build([1, 2, 3, 4], 1)))
    print(has_cycle(build([1, 2, 3, 4], -1)))
