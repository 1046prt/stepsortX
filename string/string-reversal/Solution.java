// sortsort · String Reversal
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/string-reversal

public class Main {
    // Two pointers swap characters while moving toward the middle.
    static void reverseString(char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }

    static String reversedText(String text) {
        // Java strings are immutable, so work on a char array copy.
        char[] chars = text.toCharArray();
        reverseString(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] tests = {"hello", "algorithm", "racecar", ""};
        for (String text : tests) {
            System.out.println(text + " -> " + reversedText(text));
        }
    }
}
