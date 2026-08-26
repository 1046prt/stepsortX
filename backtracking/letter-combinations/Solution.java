// Stepsort · Letter Combinations
// Category: Backtracking
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/letter-combinations

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String[] PHONE = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    static void backtrack(String digits, int idx, StringBuilder current, List<String> results) {
        if (idx == digits.length()) {
            results.add(current.toString());
            return;
        }
        String options = PHONE[digits.charAt(idx) - '0'];
        for (int i = 0; i < options.length(); i++) {
            current.append(options.charAt(i));
            backtrack(digits, idx + 1, current, results);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        backtrack("23", 0, new StringBuilder(), results);
        System.out.println(results);
        System.out.println("Total: " + results.size());
    }
}
