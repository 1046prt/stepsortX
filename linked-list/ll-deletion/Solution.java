// Stepsort · Deletion
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-deletion

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Remove the first node whose value equals target.
    static Node deleteByValue(Node head, int target) {
        if (head == null) return null;
        if (head.val == target) return head.next;
        Node curr = head;
        while (curr.next != null && curr.next.val != target) {
            curr = curr.next;
        }
        if (curr.next != null) curr.next = curr.next.next;
        return head;
    }

    // Remove the node at the given 0-based index.
    static Node deleteAtPosition(Node head, int pos) {
        if (head == null) return null;
        if (pos == 0) return head.next;
        Node curr = head;
        for (int i = 0; i < pos - 1; i++) {
            curr = curr.next;
            if (curr == null) return head;  // position out of range: ignore
        }
        if (curr.next != null) curr.next = curr.next.next;
        return head;
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
        Node head = buildList(new int[]{4, 2, 6, 2, 9});
        System.out.print("start: ");
        printList(head);

        head = deleteByValue(head, 2);
        System.out.print("delete value 2: ");
        printList(head);

        head = deleteByValue(head, 4);
        System.out.print("delete value 4: ");
        printList(head);

        head = deleteAtPosition(head, 1);
        System.out.print("delete index 1: ");
        printList(head);

        head = deleteAtPosition(head, 0);
        System.out.print("delete index 0: ");
        printList(head);
    }
}
