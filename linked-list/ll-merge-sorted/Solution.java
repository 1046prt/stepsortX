// Stepsort · Merge Two Sorted Lists
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-merge-sorted

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Attach the smaller front node to a dummy tail each round.
    static Node mergeSortedLists(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    static Node buildList(int[] values) {
        Node head = null, tail = null;
        for (int v : values) {
            Node node = new Node(v);
            if (head == null) { head = node; tail = node; }
            else { tail.next = node; tail = node; }
        }
        return head;
    }

    static void printList(Node head) {
        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Node curr = head; curr != null; curr = curr.next) {
            if (sb.length() > 0) sb.append(" -> ");
            sb.append(curr.val);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Node a = buildList(new int[]{1, 3, 5, 7});
        Node b = buildList(new int[]{2, 4, 6, 8});
        Node merged = mergeSortedLists(a, b);
        System.out.print("list a: ");
        printList(a);
        System.out.print("list b: ");
        printList(b);
        System.out.print("merged: ");
        printList(merged);
    }
}
