// Stepsort · Closest Pair of Points
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/closest-pair

import java.util.*;

public class Main {

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static double distSq(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    static double bruteForce(List<Point> pts, int lo, int hi) {
        double best = Double.MAX_VALUE;
        for (int i = lo; i < hi; i++)
            for (int j = i + 1; j < hi; j++)
                best = Math.min(best, distSq(pts.get(i), pts.get(j)));
        return best;
    }

    // points in [lo, hi) must be sorted by x
    static double closestPair(List<Point> px, int lo, int hi) {
        if (hi - lo <= 3) return bruteForce(px, lo, hi);
        int mid = (lo + hi) / 2;
        double midX = px.get(mid).x;
        double best = Math.min(closestPair(px, lo, mid), closestPair(px, mid, hi));
        List<Point> strip = new ArrayList<>();
        for (int i = lo; i < hi; i++)
            if ((px.get(i).x - midX) * (px.get(i).x - midX) < best)
                strip.add(px.get(i));
        strip.sort((a, b) -> Double.compare(a.y, b.y));
        for (int i = 0; i < strip.size(); i++)
            for (int j = i + 1; j < strip.size(); j++) {
                double dy = strip.get(j).y - strip.get(i).y;
                if (dy * dy >= best) break;
                best = Math.min(best, distSq(strip.get(i), strip.get(j)));
            }
        return best;
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>(List.of(
                new Point(2, 3), new Point(12, 30), new Point(40, 50),
                new Point(5, 1), new Point(12, 10), new Point(3, 4)));
        points.sort((a, b) -> Double.compare(a.x, b.x));
        System.out.println("minimum distance: "
                + String.format("%.6f", Math.sqrt(closestPair(points, 0, points.size()))));
    }
}
