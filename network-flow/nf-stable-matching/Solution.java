// sortsort · Gale-Shapley (Stable Matching)
// Category: Network Flow & Matching
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-stable-matching

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int indexOf(int[] list, int value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == value) return i;
        }
        return -1;
    }

    static int[] galeShapley(int[][] menPref, int[][] womenPref) {
        int n = menPref.length;
        int[] nextChoice = new int[n];
        int[] fiance = new int[n];
        Arrays.fill(fiance, -1);
        Deque<Integer> freeMen = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) freeMen.push(i);
        while (!freeMen.isEmpty()) {
            int man = freeMen.pop();
            int woman = menPref[man][nextChoice[man]++];
            int rival = fiance[woman];
            if (rival == -1 || indexOf(womenPref[woman], man) < indexOf(womenPref[woman], rival)) {
                fiance[woman] = man;
                if (rival != -1) freeMen.push(rival);
            } else {
                freeMen.push(man);
            }
        }
        int[] partner = new int[n];
        for (int w = 0; w < n; w++) partner[fiance[w]] = w;
        return partner;
    }

    static boolean isStable(int[][] menPref, int[][] womenPref, int[] partner) {
        int n = partner.length;
        for (int m = 0; m < n; m++) {
            int w = partner[m];
            for (int w2 = 0; w2 < n; w2++) {
                if (w2 == w) continue;
                int m2 = indexOf(partner, w2);
                boolean manPrefers = indexOf(menPref[m], w2) < indexOf(menPref[m], w);
                boolean womanPrefers = indexOf(womenPref[w2], m) < indexOf(womenPref[w2], m2);
                if (manPrefers && womanPrefers) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] menPref = {
            {0, 1, 2},
            {1, 0, 2},
            {0, 1, 2}
        };
        int[][] womenPref = {
            {1, 0, 2},
            {0, 2, 1},
            {0, 1, 2}
        };
        int[] partner = galeShapley(menPref, womenPref);
        for (int m = 0; m < partner.length; m++) {
            System.out.println("Man " + m + " engaged to Woman " + partner[m]);
        }
        System.out.println("Matching is stable: " + (isStable(menPref, womenPref, partner) ? "yes" : "no"));
    }
}
