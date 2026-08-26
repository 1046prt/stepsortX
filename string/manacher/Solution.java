// Stepsort · Manacher's Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/manacher

import java.util.Arrays;

public class Main {
    // Longest palindromic substring in O(n) using Manacher's algorithm.
    static String manacher(String s) {
        char[] t = new char[2 * s.length() + 1];
        Arrays.fill(t, '#');
        for (int i = 0; i < s.length(); i++) t[2 * i + 1] = s.charAt(i);
        int n = t.length;
        int[] p = new int[n];
        int center = 0, right = 0, bestLen = 0, bestCenter = 0;

        for (int i = 0; i < n; i++) {
            if (i < right) p[i] = Math.min(right - i, p[2 * center - i]);
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n
                    && t[i - p[i] - 1] == t[i + p[i] + 1]) p[i]++;
            if (i + p[i] > right) { center = i; right = i + p[i]; }
            if (p[i] > bestLen) { bestLen = p[i]; bestCenter = i; }
        }
        int start = (bestCenter - bestLen) / 2;
        return s.substring(start, start + bestLen);
    }

    public static void main(String[] args) {
        String[] tests = {"babad", "cbbd", "forgeeksskeegfor"};
        for (String s : tests) System.out.println(s + " -> " + manacher(s));
    }
}
