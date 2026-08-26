// Stepsort · Prime Factorization
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/prime-factorization

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Long> primeFactors(long n) {
        List<Long> factors = new ArrayList<>();
        for (long d = 2; d * d <= n; ++d) {
            while (n % d == 0) {
                factors.add(d);
                n /= d;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    static String format(List<Long> factors) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < factors.size(); ++i) {
            if (i > 0) sb.append(" x ");
            sb.append(factors.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long[] values = {60, 100, 97, 360, 1024};
        for (long v : values) {
            System.out.println(v + " = " + format(primeFactors(v)));
        }
    }
}
