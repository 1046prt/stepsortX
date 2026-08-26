// sortsort · Reversal
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-reversal

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Walk the list flipping each next pointer backwards.
    static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;  // save the rest of the list
            curr.next = prev;       // flip one pointer
            prev = curr;            // advance prev
            curr = next;            // advance curr
        }
        return prev;
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
        Node head = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("before: 1 -> 2 -> 3 -> 4 -> 5");
        head = reverseList(head);
        System.out.print("after:  ");
        printList(head);
    }
}
