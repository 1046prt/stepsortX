// sortsort · Palindrome Check
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-palindrome

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static boolean isPalindrome(Node head) {
        // Step 1: find the middle with slow/fast pointers.
        if (head == null || head.next == null) return true;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: reverse the second half.
        Node second = reverseList(slow.next);

        // Step 3: compare the two halves.
        Node p1 = head, p2 = second;
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
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
        int[][] tests = {
            {1, 2, 3, 2, 1},
            {1, 2, 2, 1},
            {1, 2, 3},
            {},
        };
        for (int[] values : tests) {
            boolean result = isPalindrome(buildList(values));
            System.out.println(java.util.Arrays.toString(values)
                + " is palindrome: " + result);
        }
    }
}
