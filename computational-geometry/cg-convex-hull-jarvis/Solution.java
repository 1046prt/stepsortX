// Stepsort · Jarvis March
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-jarvis

import java.util.*;

public class Main {
    static class Pt {
        double x, y;
        Pt(double x, double y) { this.x = x; this.y = y; }
    }

    static double cross(Pt o, Pt a, Pt b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
    }

    static double dist2(Pt a, Pt b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    static List<Pt> jarvisMarch(List<Pt> pts) {
        int n = pts.size(), start = 0;
        for (int i = 1; i < n; i++)
            if (pts.get(i).x < pts.get(start).x) start = i;
        List<Pt> hull = new ArrayList<>();
        int p = start;
        do {
            hull.add(pts.get(p));
            int q = (p + 1) % n;
            for (int i = 0; i < n; i++) {
                double c = cross(pts.get(p), pts.get(i), pts.get(q));
                if (c > 0 || (c == 0 && dist2(pts.get(p), pts.get(i)) > dist2(pts.get(p), pts.get(q))))
                    q = i;
            }
            p = q;
        } while (p != start);
        return hull;
    }

    public static void main(String[] args) {
        List<Pt> pts = Arrays.asList(
            new Pt(0, 0), new Pt(4, 0), new Pt(4, 3), new Pt(0, 3), new Pt(2, 1));
        System.out.print("hull:");
        for (Pt p : jarvisMarch(pts)) System.out.print(" (" + (int) p.x + "," + (int) p.y + ")");
        System.out.println();
    }
}
