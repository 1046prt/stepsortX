// Stepsort · Cycle Detection
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-cycle-detection

public class Main {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    // Slow moves 1 step, fast moves 2 steps.
    // They meet iff the list contains a cycle.
    static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // After the pointers meet, restart one at the head;
    // advancing both 1 step meets again at the cycle entry.
    static Node findCycleStart(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        System.out.println("plain list has cycle: " + hasCycle(a));

        e.next = b;  // tail links back to value 2
        System.out.println("linked tail has cycle: " + hasCycle(a));
        Node start = findCycleStart(a);
        System.out.println("cycle starts at value: " + (start != null ? start.val : -1));

        e.next = null;  // break the cycle again
        System.out.println("after breaking, has cycle: " + hasCycle(a));
    }
}
