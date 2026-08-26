// sortsort · Merge K Sorted Lists
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-k-sorted-lists

public class Main {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        int n = lists.length;
        for (int interval = 1; interval < n; interval *= 2) {  // divide and conquer
            for (int i = 0; i + interval < n; i += interval * 2) {
                lists[i] = mergeTwo(lists[i], lists[i + interval]);
            }
        }
        return lists[0];
    }

    static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode merged = mergeKLists(new ListNode[]{
            buildList(1, 4, 5),
            buildList(1, 3, 4),
            buildList(2, 6)
        });
        System.out.println(toString(merged));
    }
}
