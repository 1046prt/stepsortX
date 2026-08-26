// sortsort · Sparse Table (RMQ)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sparse-table

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 2, 8, 1, 6, 3, 7, 5};
        int n = arr.length;
        int LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        int[][] sp = new int[LOG][n];
        sp[0] = arr.clone();
        for (int j = 1; j < LOG; j++) {
            int len = 1 << j;
            for (int i = 0; i + len <= n; i++)
                sp[j][i] = Math.min(sp[j - 1][i], sp[j - 1][i + (len >> 1)]);
        }
        java.util.function.BiFunction<Integer, Integer, Integer> query =
            (l, r) -> {
                int k = (int) (Math.log(r - l + 1) / Math.log(2));
                return Math.min(sp[k][l], sp[k][r - (1 << k) + 1]);
            };
        System.out.println(query.apply(2, 6));   // 1
        System.out.println(query.apply(0, 3));   // 2
    }
}
