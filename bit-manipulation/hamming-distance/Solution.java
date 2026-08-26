// sortsort · Hamming Distance
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hamming-distance

public class Main {
    static int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int count = 0;
        while (diff != 0) {
            diff &= diff - 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] pairs = {{1, 4}, {3, 1}, {0, 255}, {93, 73}};
        for (int[] p : pairs) {
            System.out.println(p[0] + " vs " + p[1] + " -> "
                    + hammingDistance(p[0], p[1]));
        }
    }
}
