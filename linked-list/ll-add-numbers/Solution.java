// Stepsort · Add Two Numbers
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-add-numbers

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

    // Digits are least significant first, so add pairwise with carry.
    static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int total = carry;
            if (l1 != null) {
                total += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                total += l2.val;
                l2 = l2.next;
            }
            carry = total / 10;
            tail.next = new Node(total % 10);
            tail = tail.next;
        }
        return dummy.next;
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
        printList(addTwoNumbers(build(new int[]{2, 4, 3}),
                               build(new int[]{5, 6, 4})));
        printList(addTwoNumbers(build(new int[]{9, 9, 9, 9}),
                               build(new int[]{1})));
    }
}
