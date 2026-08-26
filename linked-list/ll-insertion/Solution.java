// sortsort · Insertion
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-insertion

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node insertAtHead(Node head, int val) {
        Node node = new Node(val);
        node.next = head;
        return node;
    }

    static Node insertAtTail(Node head, int val) {
        Node node = new Node(val);
        if (head == null) return node;
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return head;
    }

    static Node insertAtPosition(Node head, int pos, int val) {
        if (pos <= 0) return insertAtHead(head, val);
        Node curr = head;
        for (int i = 0; i < pos - 1 && curr != null; i++) curr = curr.next;
        if (curr == null) return head;  // position out of range: ignore
        Node node = new Node(val);
        node.next = curr.next;
        curr.next = node;
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
        Node head = null;
        head = insertAtHead(head, 3);
        System.out.print("insert 3 at head: ");
        printList(head);
        head = insertAtHead(head, 1);
        System.out.print("insert 1 at head: ");
        printList(head);
        head = insertAtTail(head, 7);
        System.out.print("insert 7 at tail: ");
        printList(head);
        head = insertAtPosition(head, 2, 5);
        System.out.print("insert 5 at index 2: ");
        printList(head);
        head = insertAtPosition(head, 0, 0);
        System.out.print("insert 0 at index 0: ");
        printList(head);
    }
}
