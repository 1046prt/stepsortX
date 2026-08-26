// Stepsort · Möbius Function
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-mobius-function

public class Main {
    static int[] spf, mu;

    static void buildMobius(int n) {
        // smallest-prime-factor sieve
        spf = new int[n + 1];
        for (int i = 0; i <= n; i++) spf[i] = i;
        for (int i = 2; (long) i * i <= n; i++)
            if (spf[i] == i)
                for (int j = i * i; j <= n; j += i)
                    if (spf[j] == j) spf[j] = i;
        mu = new int[n + 1];
        mu[1] = 1;
        // strip smallest prime p from i = p*rest; p | rest means p^2 | i
        for (int i = 2; i <= n; i++) {
            int p = spf[i];
            int rest = i / p;
            mu[i] = (rest % p == 0) ? 0 : -mu[rest];
        }
    }

    public static void main(String[] args) {
        int n = 20;
        buildMobius(n);
        StringBuilder idx = new StringBuilder("n : ");
        StringBuilder val = new StringBuilder("mu: ");
        for (int i = 1; i <= n; i++) {
            idx.append(String.format("%4d", i));
            val.append(String.format("%4d", mu[i]));
        }
        System.out.println(idx.toString());
        System.out.println(val.toString());
        long mertens = 0;
        for (int i = 1; i <= n; i++) mertens += mu[i];
        System.out.println("Mertens M(" + n + ") = " + mertens);
    }
}
