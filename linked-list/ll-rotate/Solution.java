// Stepsort · Rotate List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-rotate

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

    // Reduce k modulo the length, then cut the last k nodes off
    // and splice them onto the front.
    static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int length = 1;
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        k %= length;
        if (k == 0) return head;
        Node newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        Node newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

    static void printList(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Node head = build(new int[]{1, 2, 3, 4, 5});
        head = rotateRight(head, 2);
        printList(head);
        head = rotateRight(head, 12);
        printList(head);
    }
}
