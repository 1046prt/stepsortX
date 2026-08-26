// sortsort · Longest Palindromic Substring
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/longest-palindrome

public class Main {
    // Grow the window while it stays a palindrome and stays in bounds.
    // Returns {start, length} of the widest palindrome found.
    static int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[] {left + 1, right - left - 1};
    }

    // Every palindrome has a center: a character (odd length) or a gap
    // between two characters (even length). Try all 2n - 1 centers.
    static String longestPalindrome(String s) {
        int bestStart = 0, bestLen = 0;
        for (int center = 0; center < s.length(); center++) {
            int[] odd = expandAroundCenter(s, center, center);
            int[] even = expandAroundCenter(s, center, center + 1);
            if (odd[1] > bestLen) {
                bestStart = odd[0];
                bestLen = odd[1];
            }
            if (even[1] > bestLen) {
                bestStart = even[0];
                bestLen = even[1];
            }
        }
        return s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        String[] tests = {"babad", "cbbd", "forgeeksskeegfor"};
        for (String text : tests) {
            System.out.println(text + " -> " + longestPalindrome(text));
        }
    }
}
