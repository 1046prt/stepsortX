// sortsort · Flatten Multi-Level List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-flatten

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static class Node {
        int val;
        Node next;
        Node down;
        Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

    // Depth-first: finish a whole child chain before visiting its sibling.
    static Node flatten(Node head) {
        if (head == null) return null;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);
        Node dummy = new Node(0, null, null);
        Node tail = dummy;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            tail.next = node;
            tail = node;
            if (node.next != null) stack.push(node.next);
            if (node.down != null) stack.push(node.down);
            node.next = null;
            node.down = null;
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
        Node n7 = new Node(7, null, null);
        Node n6 = new Node(6, null, n7);
        Node n5 = new Node(5, null, n6);
        Node n8 = new Node(8, null, null);
        Node n4 = new Node(4, null, n8);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n3, n5);
        Node n1 = new Node(1, n2, null);

        printList(flatten(n1));
    }
}
