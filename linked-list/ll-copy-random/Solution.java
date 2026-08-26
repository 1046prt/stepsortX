// sortsort · Copy with Random Pointer
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-copy-random

public class Main {
    static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; }
    }

    // Deep copy using interleaving, O(1) extra space beyond the clones.
    static Node copyRandomList(Node head) {
        if (head == null) return null;

        // Insert a clone right after every original node.
        for (Node cur = head; cur != null; cur = cur.next.next) {
            Node clone = new Node(cur.val);
            clone.next = cur.next;
            cur.next = clone;
        }

        // Aim each clone's random at the clone of its target.
        for (Node cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }

        // Unweave the interleaved original and cloned chains apart.
        Node dummy = new Node(0);
        Node tail = dummy;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node clone = cur.next;
            cur.next = clone.next;
            tail.next = clone;
            tail = clone;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append("(").append(head.val).append(", random=");
            sb.append(head.random != null ? head.random.val : "null");
            sb.append(")");
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        a.random = c;
        b.random = a;
        c.random = b;

        Node copied = copyRandomList(a);
        printList(copied);
    }
}
