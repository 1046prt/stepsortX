// sortsort · Rolling Hash Collision Demo
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash-collision

long long rollingHash(string s, long long base, long long mod) {
    long long h = 0;
    for (char c : s) h = (h * base + c) % mod;
    return h;
}

pair<string,string> findCollision() {
    const long long B = 26, M = 101;
    unordered_map<long long,string> seen;
    for (int i = 1; i < 10000; i++) {
        string s; int n = i;
        while (n > 0) { s = char('a' + n % 26) + s; n /= 26; }
        long long h = rollingHash(s, B, M);
        if (seen.count(h)) return {seen[h], s};
        seen[h] = s;
    }
    return {"", ""};
}

// Dual-hash: use two moduli to reduce collision probability
long long h1 = rollingHash(s, 26, 1e9+7);
long long h2 = rollingHash(s, 31, 1e9+9);
