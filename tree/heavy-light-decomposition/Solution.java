// sortsort · Heavy-Light Decomposition
// Category: Tree
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heavy-light-decomposition

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 12;
        int[] parent = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 7};
        List<List<Integer>> kids = new ArrayList<>();
        for (int i = 0; i < n; i++) kids.add(new ArrayList<>());
        for (int v = 0; v < n; v++)
            if (parent[v] >= 0) kids.get(parent[v]).add(v);

        int[] depth = new int[n], size = new int[n];
        Arrays.fill(size, 1);
        for (int v = 0; v < n; v++) {
            int cur = v, d = 0;
            while (parent[cur] != -1) { cur = parent[cur]; d++; }
            depth[v] = d;
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> depth[b] - depth[a]);
        for (int v : order) for (int c : kids.get(v)) size[v] += size[c];

        int[] chainId = new int[n], head = new int[n];
        Arrays.fill(chainId, -1);
        int chains = 0;
        Deque<Integer> roots = new ArrayDeque<>(List.of(0));
        while (!roots.isEmpty()) {
            int start = roots.pop();
            head[start] = start;
            chainId[start] = chains;
            int cur = start;
            while (!kids.get(cur).isEmpty()) {
                int heavy = kids.get(cur).get(0);
                for (int c : kids.get(cur))
                    if (size[c] > size[heavy]) heavy = c;
                head[heavy] = head[cur];
                chainId[heavy] = chains;
                cur = heavy;
                for (int c : kids.get(cur))
                    if (c != heavy) roots.push(c);
            }
            chains++;
        }
        System.out.println(chains + " chains");
    }
