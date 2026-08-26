// Stepsort · Z Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/z-algorithm

import java.util.Arrays;

public class Main {
    // z[i] = length of the longest common prefix of s and s[i..].
    static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = n;
        int left = 0, right = 0; // rightmost match window found so far
        for (int i = 1; i < n; i++) {
            if (i < right) { // reuse information from the previous window
                z[i] = Math.min(right - i, z[i - left]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] > right) { // extend the match window
                left = i;
                right = i + z[i];
            }
        }
        return z;
    }

    public static void main(String[] args) {
        String text = "aabxaabxcaabxaabxay";
        String pattern = "aabx";

        // A '#' separator never appears in the data, so any maximal
        // prefix match crossing it must end exactly at the separator.
        String combined = pattern + "#" + text;
        int[] z = zFunction(combined);
        int m = pattern.length();

        StringBuilder matches = new StringBuilder();
        for (int i = m + 1; i < combined.length(); i++) {
            if (z[i] == m) {
                if (matches.length() > 0) matches.append(" ");
                matches.append(i - m - 1);
            }
        }

        System.out.println("combined: " + combined);
        System.out.println("z-array: " + Arrays.toString(z));
        System.out.println("matches: " + matches.toString());
    }
}
