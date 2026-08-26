// Stepsort · Climbing Stairs
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/climbing-stairs

public class Main {
    public static long climbStairs(long n) {
        // Ways(n) follows Fibonacci; O(1) space iterative.
        long prev = 1, curr = 1;
        for (long i = 0; i < n - 1; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(10)); // 89
    }
}
