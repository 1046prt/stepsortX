// sortsort · Boyer-Moore
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/boyer-moore

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final int ALPHABET_SIZE = 256;

    static int[] badCharacterTable(String pattern) {
        int[] table = new int[ALPHABET_SIZE];
        Arrays.fill(table, -1);
        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;
        }
        return table;
    }

    static List<Integer> boyerMooreSearch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return matches;
        int[] bad = badCharacterTable(pattern);
        int shift = 0;

        while (shift <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) j--;
            if (j < 0) {
                matches.add(shift);
                if (shift + m < n) shift += m - bad[text.charAt(shift + m)];
                else shift += 1;
            } else {
                shift += Math.max(1, j - bad[text.charAt(shift + j)]);
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(boyerMooreSearch("ABAAABCDABABCD", "ABC"));
        System.out.println(boyerMooreSearch("AABAACAADAABAABA", "AABA"));
    }
}
