// Stepsort · Sweep Line Intersections
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-sweep-line

import java.util.*;

public class Main {
    static int orientation(double[] a, double[] b, double[] c) {
        double v = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
        return v == 0 ? 0 : (v > 0 ? 1 : 2);
    }

    static boolean onSegment(double[] a, double[] b, double[] p) {
        return Math.min(a[0], b[0]) <= p[0] && p[0] <= Math.max(a[0], b[0]) &&
               Math.min(a[1], b[1]) <= p[1] && p[1] <= Math.max(a[1], b[1]);
    }

    static boolean segmentsIntersect(double[][] s1, double[][] s2) {
        double[] p1 = s1[0], p2 = s1[1], p3 = s2[0], p4 = s2[1];
        int o1 = orientation(p1, p2, p3);
        int o2 = orientation(p1, p2, p4);
        int o3 = orientation(p3, p4, p1);
        int o4 = orientation(p3, p4, p2);
        if (o1 != o2 && o3 != o4) return true;
        if (o1 == 0 && onSegment(p1, p2, p3)) return true;
        if (o2 == 0 && onSegment(p1, p2, p4)) return true;
        if (o3 == 0 && onSegment(p3, p4, p1)) return true;
        if (o4 == 0 && onSegment(p3, p4, p2)) return true;
        return false;
    }

    static List<int[]> sweepLineIntersections(List<double[][]> segments) {
        int n = segments.size();
        double[][] evX = new double[2 * n][];
        int[][] evMeta = new int[2 * n][2];
        int k = 0;
        for (int i = 0; i < n; i++) {
            double[][] s = segments.get(i);
            double[] a = s[0], b = s[1];
            evX[k] = new double[]{a[0]};
            evMeta[k] = new int[]{0, i};
            k++;
            evX[k] = new double[]{b[0]};
            evMeta[k] = new int[]{1, i};
            k++;
        }
        Integer[] order = new Integer[2 * n];
        for (int i = 0; i < 2 * n; i++) order[i] = i;
        Arrays.sort(order, (p, q) -> {
            int c = Double.compare(evX[p][0], evX[q][0]);
            if (c != 0) return c;
            return Integer.compare(evMeta[p][0], evMeta[q][0]);
        });
        List<Integer> active = new ArrayList<>();
        TreeSet<Long> found = new TreeSet<>();
        for (int oi = 0; oi < 2 * n; oi++) {
            int i = evMeta[order[oi]][1];
            int kind = evMeta[order[oi]][0];
            if (kind == 0) {
                for (int j : active)
                    if (segmentsIntersect(segments.get(i), segments.get(j)))
                        found.add((long) Math.min(i, j) * 100000L + Math.max(i, j));
                active.add(i);
            } else {
                active.remove(Integer.valueOf(i));
            }
        }
        List<int[]> out = new ArrayList<>();
        for (long f : found) out.add(new int[]{(int) (f / 100000L), (int) (f % 100000L)});
        return out;
    }

    public static void main(String[] args) {
        List<double[][]> segs = List.of(
            new double[][]{{0, 0}, {4, 4}},
            new double[][]{{4, 0}, {0, 4}},
            new double[][]{{5, 5}, {7, 7}},
            new double[][]{{0, 5}, {5, 0}});
        System.out.print("intersecting pairs:");
        for (int[] p : sweepLineIntersections(segs))
            System.out.print(" (" + p[0] + "," + p[1] + ")");
        System.out.println();
    }
}
