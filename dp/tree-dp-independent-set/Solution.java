// sortsort · Tree DP (Max Independent Set)
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/tree-dp-independent-set

import java.util.*;

public class Main {
    static long[] weight_, takeDp, skipDp;
    static List<List<Integer>> kids;

    static void dfs(int v) {
        long take = weight_[v], skip = 0;
        for (int c : kids.get(v)) {
            dfs(c);
            take += skipDp[c];
            skip += Math.max(takeDp[c], skipDp[c]);
        }
        takeDp[v] = take;
        skipDp[v] = skip;
    }

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
        weight_ = new long[]{3, 2, 1, 4, 2, 1, 3, 2, 5, 1, 2, 4};
        int n = parent.length;
        kids = new ArrayList<>();
        for (int i = 0; i < n; i++) kids.add(new ArrayList<>());
        for (int v = 0; v < n; v++)
            if (parent[v] >= 0) kids.get(parent[v]).add(v);
        takeDp = new long[n]; skipDp = new long[n];
        dfs(0);
        System.out.println("max independent set weight: " + Math.max(takeDp[0], skipDp[0]));
    }
}
