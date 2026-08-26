// Stepsort · Aho-Corasick Failure Links
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick-failure-links

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] patterns = {"he", "she", "his", "hers"};
        List<Map<Character, Integer>> go = new ArrayList<>(List.of(new HashMap<>()));
        List<Integer> fail = new ArrayList<>(List.of(0));
        List<List<String>> out = new ArrayList<>(List.of(new ArrayList<>()));

        for (String p : patterns) {
            int cur = 0;
            for (char c : p.toCharArray()) {
                if (!go.get(cur).containsKey(c)) {
                    go.get(cur).put(c, go.size());
                    go.add(new HashMap<>()); fail.add(0); out.add(new ArrayList<>());
                }
                cur = go.get(cur).get(c);
            }
            out.get(cur).add(p);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (var e : go.get(0).entrySet()) { fail.set(e.getValue(), 0); q.add(e.getValue()); }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (var e : go.get(u).entrySet()) {
                int v = e.getValue(), f = fail.get(u);
                char c = e.getKey();
                while (f != 0 && !go.get(f).containsKey(c)) f = fail.get(f);
                int target = go.get(f).getOrDefault(c, 0);
                fail.set(v, target == v ? 0 : target);
                out.get(v).addAll(out.get(fail.get(v)));
                q.add(v);
            }
        }
        String text = "ushers";
        int cur = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            while (cur != 0 && !go.get(cur).containsKey(c)) cur = fail.get(cur);
            cur = go.get(cur).getOrDefault(c, 0);
            for (String p : out.get(cur))
                System.out.println(p + " @ " + (i - p.length() + 1) + ".." + i);
        }
    }
}
