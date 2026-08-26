// Stepsort · Graham Scan
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-convex-hull-graham

import java.util.*;

public class Main {
    static class Pt {
        double x, y;
        Pt(double x, double y) { this.x = x; this.y = y; }
    }

    static double cross(Pt o, Pt a, Pt b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
    }

    static List<Pt> grahamScan(List<Pt> pts) {
        int idx = 0;
        for (int i = 1; i < pts.size(); i++) {
            Pt p = pts.get(i), q = pts.get(idx);
            if (p.y < q.y || (p.y == q.y && p.x < q.x)) idx = i;
        }
        Collections.swap(pts, 0, idx);
        final Pt pivot = pts.get(0);
        List<Pt> rest = new ArrayList<>(pts.subList(1, pts.size()));
        rest.sort(Comparator.comparingDouble((Pt p) -> Math.atan2(p.y - pivot.y, p.x - pivot.x)));
        List<Pt> stack = new ArrayList<>();
        stack.add(pivot);
        for (Pt p : rest) {
            while (stack.size() >= 2 &&
                    cross(stack.get(stack.size() - 2), stack.get(stack.size() - 1), p) <= 0) {
                stack.remove(stack.size() - 1);
            }
            stack.add(p);
        }
        return stack;
    }

    public static void main(String[] args) {
        List<Pt> pts = new ArrayList<>(List.of(
            new Pt(0, 0), new Pt(4, 0), new Pt(4, 3), new Pt(0, 3), new Pt(2, 1)));
        System.out.print("hull:");
        for (Pt p : grahamScan(pts)) System.out.print(" (" + (int) p.x + "," + (int) p.y + ")");
        System.out.println();
    }
}
