// sortsort · Find Intersection
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-intersection

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node build(int[] values) {
        Node dummy = new Node(0);
        Node tail = dummy;
        for (int v : values) {
            tail.next = new Node(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    static int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // Length alignment: start both walks equally far from the shared tail.
    static Node getIntersectionNode(Node a, Node b) {
        int lenA = getLength(a);
        int lenB = getLength(b);
        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }
        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }
        // Advance together until the two references meet (or both run out).
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        Node shared = build(new int[]{8, 10});
        Node a = build(new int[]{3, 7});
        Node tailA = a;
        while (tailA.next != null) tailA = tailA.next;
        tailA.next = shared;              // A: 3 -> 7 -> 8 -> 10

        Node b = build(new int[]{99});
        b.next = shared;                  // B: 99 -> 8 -> 10

        Node hit = getIntersectionNode(a, b);
        System.out.println("intersecting lists meet at: "
                + (hit != null ? hit.val : "none"));

        Node other = build(new int[]{5, 6});
        Node miss = getIntersectionNode(a, other);
        System.out.println("disjoint lists meet at: "
                + (miss != null ? miss.val : "none"));
    }
}
