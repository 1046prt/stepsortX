// Stepsort · Count Set Bits
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-set-bits

public class Main {
    // Brian Kernighan: n & (n - 1) clears the lowest set bit
    static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] values = {0, 1, 7, 13, 255, 1023};
        for (int v : values) {
            System.out.println(v + ": kernighan=" + countSetBits(v)
                    + " builtin=" + Integer.bitCount(v));
        }
    }
}
