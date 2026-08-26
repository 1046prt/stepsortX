// Stepsort · Rabin-Karp
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rabin-karp

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final long BASE = 256;        // alphabet size
    static final long MOD = 100000007L;  // prime modulus

    // Slide a rolling hash over every window of length m. Hash hits
    // are verified by direct comparison, so a collision between
    // different strings can never be reported as a match.
    static List<Integer> rabinKarpSearch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return matches;

        long highOrder = 1;             // weight of the leading character
        for (int i = 1; i < m; i++) highOrder = highOrder * BASE % MOD;

        long pHash = 0, tHash = 0;      // pattern hash and window hash
        for (int i = 0; i < m; i++) {
            pHash = (pHash * BASE + pattern.charAt(i)) % MOD;
            tHash = (tHash * BASE + text.charAt(i)) % MOD;
        }

        for (int start = 0; start + m <= n; start++) {
            if (pHash == tHash && text.startsWith(pattern, start)) {
                matches.add(start);
            }
            if (start + m < n) { // roll the window one character right
                tHash = ((tHash - text.charAt(start) * highOrder) * BASE
                        + text.charAt(start + m)) % MOD;
                if (tHash < 0) tHash += MOD;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(rabinKarpSearch("ababcababd", "abab"));
        System.out.println(rabinKarpSearch("aaaaab", "aa"));
    }
}
