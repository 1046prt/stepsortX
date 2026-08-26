# Stepsort · Merge K Sorted Lists
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-k-sorted-lists

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def merge_two(a, b):
    dummy = ListNode()
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


def merge_k_lists(lists):
    if not lists:
        return None
    n = len(lists)
    interval = 1
    while interval < n:  # pairwise divide and conquer
        for i in range(0, n - interval, interval * 2):
            lists[i] = merge_two(lists[i], lists[i + interval])
        interval *= 2
    return lists[0]


def to_string(head):
    parts = []
    while head:
        parts.append(str(head.val))
        head = head.next
    return " -> ".join(parts)


if __name__ == "__main__":
    l1 = ListNode(1, ListNode(4, ListNode(5)))
    l2 = ListNode(1, ListNode(3, ListNode(4)))
    l3 = ListNode(2, ListNode(6))
    print(to_string(merge_k_lists([l1, l2, l3])))
