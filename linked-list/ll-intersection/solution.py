# sortsort · Find Intersection
# Category: Linked List
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-intersection

class Node:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def get_length(head):
    count = 0
    while head:
        count += 1
        head = head.next
    return count


def get_intersection_node(head_a, head_b):
    # Length alignment: start both walks equally far from the shared tail.
    len_a = get_length(head_a)
    len_b = get_length(head_b)
    while len_a > len_b:
        head_a = head_a.next
        len_a -= 1
    while len_b > len_a:
        head_b = head_b.next
        len_b -= 1
    # Advance together until the two pointers meet (or both run out).
    while head_a is not head_b:
        head_a = head_a.next
        head_b = head_b.next
    return head_a


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
    print(" -> ".join(parts) if parts else "empty")


if __name__ == "__main__":
    shared = build([8, 10])
    list_a = build([3, 7])
    tail_a = list_a
    while tail_a.next:
        tail_a = tail_a.next
    tail_a.next = shared              # A: 3 -> 7 -> 8 -> 10

    list_b = build([99])
    list_b.next = shared              # B: 99 -> 8 -> 10

    hit = get_intersection_node(list_a, list_b)
    print("intersecting lists meet at:", hit.val if hit else "none")

    other = build([5, 6])
    miss = get_intersection_node(list_a, other)
    print("disjoint lists meet at:", miss.val if miss else "none")
