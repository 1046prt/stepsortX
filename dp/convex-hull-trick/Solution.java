// Stepsort · Convex Hull Trick
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/convex-hull-trick

import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Line {
        long m, b;
        Line(long m, long b) { this.m = m; this.b = b; }
    }

    static boolean bad(Line a, Line b, Line c) {
        return (c.b - a.b) * (a.m - b.m) <= (b.b - a.b) * (a.m - c.m);
    }

    static long value(Line l, long x) { return l.m * x + l.b; }

    public static void main(String[] args) {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(5, 0));
        lines.add(new Line(3, 4));
        lines.add(new Line(1, 7));
        lines.add(new Line(-1, 12));
        List<Line> hull = new ArrayList<>();
        for (Line ln : lines) {
            while (hull.size() >= 2 &&
                   bad(hull.get(hull.size() - 2), hull.get(hull.size() - 1), ln))
                hull.remove(hull.size() - 1);
            hull.add(ln);
        }
        int ptr = 0;
        int[] xs = {0, 2, 5};
        for (int x : xs) {
            while (ptr + 1 < hull.size() &&
                   value(hull.get(ptr + 1), x) <= value(hull.get(ptr), x))
                ptr++;
            System.out.println("min f(" + x + ") = " + value(hull.get(ptr), x));
        }
    }
}
