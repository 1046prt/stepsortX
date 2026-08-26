// Stepsort · Swap Without Temp
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-swap

public class Main {
    static int[] xorSwap(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        int x = 3, y = 9;
        System.out.println("before: " + x + " " + y);
        int[] swapped = xorSwap(x, y);
        System.out.println("after: " + swapped[0] + " " + swapped[1]);
    }
}
