# sortsort · Rolling Hash Collision Demo
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rolling-hash-collision

def rolling_hash(s, base, mod):
    h = 0
    for c in s:
        h = (h * base + ord(c)) % mod
    return h

def find_collision(base=26, mod=101):
    seen = {}
    for i in range(1, 10000):
        s = ""
        n = i
        while n > 0:
            s = chr(97 + (n % 26)) + s
            n //= 26
        h = rolling_hash(s, base, mod)
        if h in seen:
            return seen[h], s, h
        seen[h] = s
    return None

a, b, h = find_collision()
print(f"Collision: '{a}' and '{b}' both hash to {h}")

# Dual-hash verification
MOD1, MOD2 = 10**9 + 7, 10**9 + 9
h1a, h2a = rolling_hash(a, 26, MOD1), rolling_hash(a, 31, MOD2)
h1b, h2b = rolling_hash(b, 26, MOD1), rolling_hash(b, 31, MOD2)
print(f"Dual hash: {h1a==h1b and h2a==h2b}")
