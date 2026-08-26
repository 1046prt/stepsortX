// sortsort · Remove Duplicates
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-remove-duplicates

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

    // Sorted input keeps duplicates adjacent, so one pass suffices.
    static void removeDuplicates(Node head) {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
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
        Node head = build(new int[]{1, 2, 2, 3, 4, 4, 4, 5});
        printList(head);
        removeDuplicates(head);
        printList(head);
    }
}
