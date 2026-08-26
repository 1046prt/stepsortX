// sortsort · DSU on Tree (Small-to-Large)
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/dsu-on-tree

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int n = 12;
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
        int[] color = {1, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1};
        List<List<Integer>> ch = new ArrayList<>();
        for (int v = 0; v < n; v++) ch.add(new ArrayList<>());
        int root = 0;
        for (int v = 0; v < n; v++) {
            if (parent[v] == -1) root = v;
            else ch.get(parent[v]).add(v);
        }
        int[] size = new int[n];
        Arrays.fill(size, 1);
        List<Integer> postorder = new ArrayList<>();
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{root, 0});
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            int v = top[0];
            if (top[1] == 1) {
                postorder.add(v);
                for (int c : ch.get(v)) size[v] += size[c];
            } else {
                stack.push(new int[]{v, 1});
                for (int c : ch.get(v)) stack.push(new int[]{c, 0});
            }
        }
        List<Set<Integer>> maps = new ArrayList<>();
        for (int v = 0; v < n; v++) maps.add(null);
        int[] ans = new int[n];
        for (int v : postorder) {
            int big = -1;
            for (int c : ch.get(v)) if (big == -1 || size[c] > size[big]) big = c;
            Set<Integer> cur;
            if (big == -1) {
                cur = new HashSet<>();
                cur.add(color[v]);
            } else {
                cur = maps.get(big);
                maps.set(big, null);
                cur.add(color[v]);
                for (int c : ch.get(v)) {
                    if (c != big) {
                        cur.addAll(maps.get(c));
                        maps.set(c, null);
                    }
                }
            }
            maps.set(v, cur);
            ans[v] = cur.size();
        }
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < n; v++) {
            if (v > 0) sb.append(" ");
            sb.append(ans[v]);
        }
        System.out.println(sb);
    }
}
