// Stepsort · Eertree (Palindromic Tree)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/eertree

import java.util.*;

public class Main {
    static class PalNode { int len, suff; PalNode(int l, int s){ len=l; suff=s; } }

    public static void main(String[] args) {
        String s = "abba";
        List<PalNode> tree = new ArrayList<>(List.of(new PalNode(-1,0), new PalNode(0,0)));
        int last = 1;
        List<String> created = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int x = last;
            while (true) {
                int start = i - tree.get(x).len - 1;
                if (start >= 0 && s.charAt(start) == c) break;
                x = tree.get(x).suff;
            }
            int candLen = tree.get(x).len + 2;
            int found = -1;
            for (int t = 2; t < tree.size(); t++)
                if (tree.get(t).len == candLen) { found = t; break; }
            if (found != -1) { last = found; continue; }

            int suffLen = Math.max(tree.get(x).len, 0);
            int target = 1;
            for (int t = 2; t < tree.size(); t++)
                if (tree.get(t).len == suffLen) { target = t; break; }
            tree.add(new PalNode(candLen, target));
            last = tree.size() - 1;
            created.add("len " + candLen);
        }
        for (String c : created) System.out.println(c);
        System.out.println("distinct: " + (tree.size() - 2));
    }
}
