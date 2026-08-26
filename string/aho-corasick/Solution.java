// Stepsort · Aho-Corasick
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/aho-corasick

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
    static final int ALPHA = 26;
    static int[][] go;
    static int[] fail;
    static List<List<Integer>> out;
    static String[] pats;

    static int newNode() {
        int[] row = new int[ALPHA];
        Arrays.fill(row, -1);
        go = Arrays.copyOf(go, go.length + 1);
        go[go.length - 1] = row;
        out.add(new ArrayList<>());
        return go.length - 1;
    }

    static void buildAutomaton(String[] patterns) {
        pats = patterns;
        go = new int[1][ALPHA];
        Arrays.fill(go[0], -1);
        out = new ArrayList<>();
        out.add(new ArrayList<>());

        for (int idx = 0; idx < patterns.length; idx++) {
            int node = 0;
            for (char ch : patterns[idx].toCharArray()) {
                int c = ch - 'a';
                if (go[node][c] == -1) go[node][c] = newNode();
                node = go[node][c];
            }
            out.get(node).add(idx);
        }

        fail = new int[go.length];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int c = 0; c < ALPHA; c++) {
            if (go[0][c] == -1) go[0][c] = 0;
            else q.add(go[0][c]);
        }
        while (!q.isEmpty()) {
            int r = q.poll();
            for (int c = 0; c < ALPHA; c++) {
                int u = go[r][c];
                if (u == -1) {
                    go[r][c] = go[fail[r]][c];
                } else {
                    fail[u] = go[fail[r]][c];
                    if (fail[u] == u) fail[u] = 0;
                    out.get(u).addAll(out.get(fail[u]));
                    q.add(u);
                }
            }
        }
    }

    static void search(String text) {
        int state = 0;
        for (int i = 0; i < text.length(); i++) {
            state = go[state][text.charAt(i) - 'a'];
            for (int idx : out.get(state)) {
                int start = i - pats[idx].length() + 1;
                System.out.println(pats[idx] + " at index " + start);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ahishers";
        System.out.println("text: " + text);
        buildAutomaton(new String[] {"he", "she", "his", "hers"});
        search(text);
    }
}
