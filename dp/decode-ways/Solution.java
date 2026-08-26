// Stepsort · Decode Ways
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/decode-ways

public class Main {
    // prev2 = ways for prefix ending two chars back, prev1 = one char back
    static int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        long prev2 = 1, prev1 = 1;
        for (int i = 1; i < s.length(); i++) {
            long cur = 0;
            if (s.charAt(i) != '0') cur += prev1;  // single digit decode
            int two = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (two >= 10 && two <= 26) cur += prev2;  // two digit decode
            prev2 = prev1;
            prev1 = cur;
        }
        return (int) prev1;
    }

    public static void main(String[] args) {
        String[] tests = {"12", "226", "06"};
        for (String t : tests) {
            System.out.println(t + " decodes in " + numDecodings(t) + " ways");
        }
    }
}
