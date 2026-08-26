// Stepsort · Closest Pair (D&C)
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-closest-pair

import java.util.*;

public class Main {
    static class Pt {
        double x, y;
        Pt(double x, double y) { this.x = x; this.y = y; }
    }

    static double dist2(Pt a, Pt b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    static double closestRec(List<Pt> px, int lo, int hi) {
        if (hi - lo <= 3) {
            double best = Double.MAX_VALUE;
            for (int i = lo; i < hi; i++)
                for (int j = i + 1; j < hi; j++)
                    best = Math.min(best, dist2(px.get(i), px.get(j)));
            return best;
        }
        int mid = (lo + hi) / 2;
        double d = Math.min(closestRec(px, lo, mid), closestRec(px, mid, hi));
        double midx = px.get(mid).x;
        List<Pt> strip = new ArrayList<>();
        for (int i = lo; i < hi; i++)
            if ((px.get(i).x - midx) * (px.get(i).x - midx) < d) strip.add(px.get(i));
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++)
            for (int j = i + 1; j < strip.size(); j++) {
                double dy = strip.get(j).y - strip.get(i).y;
                if (dy * dy >= d) break;
                d = Math.min(d, dist2(strip.get(i), strip.get(j)));
            }
        return d;
    }

    public static void main(String[] args) {
        List<Pt> pts = new ArrayList<>(List.of(
            new Pt(0, 0), new Pt(5, 4), new Pt(3, 1),
            new Pt(2, 6), new Pt(8, 3), new Pt(7, 7)));
        pts.sort(Comparator.comparingDouble(p -> p.x));
        System.out.printf("closest distance: %.4f%n",
            Math.sqrt(closestRec(pts, 0, pts.size())));
    }
}
