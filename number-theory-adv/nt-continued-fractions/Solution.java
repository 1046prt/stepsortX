// sortsort · Continued Fractions
// Category: Number Theory (Adv)
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nt-continued-fractions

import java.util.ArrayList;
import java.util.List;

public class Main {
    static long floordiv(long a, long b) {
        // floor division, keeps every partial quotient well defined
        long d = a / b;
        if (a % b != 0 && ((a < 0) != (b < 0))) d--;
        return d;
    }

    // expand p/q into [a0; a1, a2, ...] with p/q = a0 + 1/(a1 + 1/(a2 + ...))
    static List<Long> continuedFraction(long p, long q) {
        List<Long> terms = new ArrayList<>();
        if (q < 0) {
            p = -p;
            q = -q;
        }
        while (q != 0) {
            long a = floordiv(p, q);
            terms.add(a);
            long r = p - a * q;
            p = q;
            q = r;
        }
        return terms;
    }

    static String fmt(List<Long> t) {
        StringBuilder sb = new StringBuilder("[");
        sb.append(t.get(0));
        for (int i = 1; i < t.size(); i++) {
            sb.append(i == 1 ? "; " : ", ");
            sb.append(t.get(i));
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        long[][] samples = {{43, 19}, {649, 200}, {5, 3}, {13, 8}, {7, 1}, {-43, 19}};
        for (long[] s : samples) {
            System.out.println(s[0] + "/" + s[1] + " -> "
                    + fmt(continuedFraction(s[0], s[1])));
        }
    }
}
