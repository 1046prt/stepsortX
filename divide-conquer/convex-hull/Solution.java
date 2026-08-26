// sortsort · Convex Hull
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull

import java.util.*;

public class Main {

    // z-component of (a - o) x (b - o)
    static long cross(long[] o, long[] a, long[] b) {
        return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0]);
    }

    // Andrew monotone chain; returns hull vertices counter-clockwise,
    // collinear points dropped
    static List<long[]> convexHull(List<long[]> input) {
        List<long[]> pts = new ArrayList<>(input);
        pts.sort((a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0])
                                        : Long.compare(a[1], b[1]));
        List<long[]> unique = new ArrayList<>();
        for (long[] p : pts) {
            long[] last = unique.isEmpty() ? null : unique.get(unique.size() - 1);
            if (last == null || last[0] != p[0] || last[1] != p[1]) unique.add(p);
        }
        int n = unique.size();
        if (n <= 2) return unique;
        List<long[]> lower = new ArrayList<>(), upper = new ArrayList<>();
        for (long[] p : unique) {
            while (lower.size() >= 2 &&
                   cross(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0)
                lower.remove(lower.size() - 1);
            lower.add(p);
        }
        for (int i = n - 1; i >= 0; i--) {
            long[] p = unique.get(i);
            while (upper.size() >= 2 &&
                   cross(upper.get(upper.size() - 2), upper.get(upper.size() - 1), p) <= 0)
                upper.remove(upper.size() - 1);
            upper.add(p);
        }
        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);
        lower.addAll(upper);
        return lower;
    }

    public static void main(String[] args) {
        List<long[]> points = new ArrayList<>(List.of(
                new long[]{0, 0}, new long[]{2, 0}, new long[]{2, 4},
                new long[]{0, 4}, new long[]{1, 1}, new long[]{1, 2}));
        System.out.println("hull vertices:");
        for (long[] p : convexHull(points))
            System.out.println(" (" + p[0] + ", " + p[1] + ")");
    }
}
