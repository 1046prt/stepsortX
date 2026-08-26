// sortsort · Sort Linked List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-sort

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

    // Slow/fast pointers: slow stops at the end of the first half.
    static Node splitMiddle(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node mergeLists(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    static Node sortList(Node head) {
        if (head == null || head.next == null) return head;
        Node mid = splitMiddle(head);
        Node rightHalf = mid.next;
        mid.next = null;
        Node left = sortList(head);
        Node right = sortList(rightHalf);
        return mergeLists(left, right);
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
        Node head = build(new int[]{5, 3, 8, 1, 9, 2, 7});
        printList(sortList(head));
    }
}
