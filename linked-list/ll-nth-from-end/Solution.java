// Stepsort · Nth from End
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-nth-from-end

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Advance first n steps, then move both pointers together;
    // when first leaves the list, second sits n from the end.
    static Node nthFromEnd(Node head, int n) {
        Node first = head;
        for (int i = 0; i < n; i++) {
            if (first == null) return null;  // n exceeds the list length
            first = first.next;
        }
        Node second = head;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
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

    public static void main(String[] args) {
        Node head = buildList(new int[]{10, 20, 30, 40, 50});
        for (int n : new int[]{1, 3, 5, 6}) {
            Node node = nthFromEnd(head, n);
            if (node != null) System.out.println(n + "-th from end: " + node.val);
            else System.out.println(n + "-th from end: not found");
        }
    }
}
