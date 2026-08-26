// sortsort · Run-Length Encoding
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/run-length-encoding

public class Main {
    static String rleEncode(String text) {
        if (text.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1)) {
                count++;
            } else {
                result.append(text.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        result.append(text.charAt(text.length() - 1)).append(count);
        return result.toString();
    }

    static String rleDecode(String encoded) {
        StringBuilder result = new StringBuilder();
        StringBuilder digits = new StringBuilder();
        for (char ch : encoded.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            } else {
                int times = Integer.parseInt(digits.toString());
                for (int k = 0; k < times; k++) result.append(ch);
                digits.setLength(0);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String original = "aaabbbcccdde";
        String encoded = rleEncode(original);
        System.out.println("original: " + original);
        System.out.println("encoded: " + encoded);
        System.out.println("decoded: " + rleDecode(encoded));
    }
}
