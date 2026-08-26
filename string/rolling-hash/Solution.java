// Stepsort · Rolling Hash (Polynomial)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash

public class Main {
    public static void main(String[] args) {
        String s = "abcabd";
        final long MOD = 1_000_000_007L, P = 31;
        int n = s.length();
        long[] h = new long[n + 1], pw = new long[n + 1];
        for (int i = 0; i < n; i++) {
            h[i + 1] = (h[i] * P + (s.charAt(i) - 'a' + 1)) % MOD;
            pw[i + 1] = (pw[i] * P) % MOD;
        }
        java.util.function.BiFunction<Integer, Integer, Long> get =
            (l, r) -> ((h[r + 1] - h[l] * pw[r - l + 1]) % MOD + MOD) % MOD;
        System.out.println("abc: " + get.apply(0, 2) + " abd: " + get.apply(3, 5));
        System.out.println("ab==ab: " + get.apply(0, 1).equals(get.apply(3, 4)));
    }
}
