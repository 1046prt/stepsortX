// Stepsort · Rolling Hash Collision Demo
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash-collision

static long rollingHash(String s, long base, long mod) {
    long h = 0;
    for (char c : s.toCharArray()) h = (h * base + c) % mod;
    return h;
}

static String[] findCollision() {
    long B = 26, M = 101;
    Map<Long, String> seen = new HashMap<>();
    for (int i = 1; i < 10000; i++) {
        StringBuilder sb = new StringBuilder();
        int n = i;
        while (n > 0) { sb.insert(0, (char)('a' + n % 26)); n /= 26; }
        String s = sb.toString();
        long h = rollingHash(s, B, M);
        if (seen.containsKey(h)) return new String[]{seen.get(h), s};
        seen.put(h, s);
    }
    return null;
}
