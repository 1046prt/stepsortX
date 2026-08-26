// sortsort · Subsets via Bitmask
// Category: Bit Manipulation
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/subset-bitmask

public class Main {
    static void printSubsets(int[] arr) {
        int n = arr.length;
        System.out.println("subsets of size " + n);
        for (int mask = 0; mask < (1 << n); mask++) {
            StringBuilder sb = new StringBuilder(mask + " -> {");
            boolean first = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (!first) sb.append(", ");
                    sb.append(arr[i]);
                    first = false;
                }
            }
            sb.append("}");
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        printSubsets(new int[]{1, 2, 3});
    }
}
