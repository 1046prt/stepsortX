// sortsort · Linked List Cycle
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linked-list-cycle

public class Main {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    static ListNode build(int[] values, int pos) {
        // pos = index the tail points back to, or -1 for no cycle.
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i + 1 < values.length; i++) nodes[i].next = nodes[i + 1];
        if (values.length > 0 && pos >= 0) nodes[values.length - 1].next = nodes[pos];
        return values.length == 0 ? null : nodes[0];
    }

    public static void main(String[] args) {
        System.out.println(hasCycle(build(new int[] {1, 2, 3, 4}, 1)));
        System.out.println(hasCycle(build(new int[] {1, 2, 3, 4}, -1)));
    }
}
