// sortsort · Reverse Bits
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/bit-reverse

public class Main {
    static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1;
        }
        return result;
    }

    // display an int as an unsigned 32-bit value via long
    static String toUnsigned(int v) {
        return Long.toString(v & 0xFFFFFFFFL);
    }

    public static void main(String[] args) {
        int[] samples = {1, 43261596, (int) 4294967280L};
        for (int v : samples) {
            System.out.println(toUnsigned(v) + " -> " + toUnsigned(reverseBits(v)));
        }
    }
}
