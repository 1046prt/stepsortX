// sortsort · Delaunay Triangulation
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-delaunay

import java.util.*;

public class Main {
    static double orient(double[] a, double[] b, double[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
    }

    static boolean inCircumcircle(double[] a, double[] b, double[] c, double[] d) {
        double adx = a[0] - d[0], ady = a[1] - d[1];
        double bdx = b[0] - d[0], bdy = b[1] - d[1];
        double cdx = c[0] - d[0], cdy = c[1] - d[1];
        double al = adx * adx + ady * ady;
        double bl = bdx * bdx + bdy * bdy;
        double cl = cdx * cdx + cdy * cdy;
        double det = adx * (bdy * cl - bl * cdy)
                   - ady * (bdx * cl - bl * cdx)
                   + al * (bdx * cdy - bdy * cdx);
        return det > 1e-7;
    }

    static List<int[]> delaunay(List<double[]> points) {
        int n = points.size();
        List<double[]> pts = new ArrayList<>(points);
        pts.add(new double[]{-100, -100});
        pts.add(new double[]{200, -100});
        pts.add(new double[]{50, 200});
        List<int[]> tris = new ArrayList<>();
        tris.add(new int[]{n, n + 1, n + 2});
        Map<Long, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int ti = tris.size() - 1; ti >= 0; ti--) {
                int[] t = tris.get(ti);
                if (!inCircumcircle(pts.get(t[0]), pts.get(t[1]), pts.get(t[2]), pts.get(i)))
                    continue;
                tris.remove(ti);
                int[][] edges = {{t[0], t[1]}, {t[1], t[2]}, {t[2], t[0]}};
                for (int[] e : edges) {
                    long key = (long) Math.min(e[0], e[1]) * 100000L + Math.max(e[0], e[1]);
                    cnt.merge(key, 1, Integer::sum);
                }
            }
            for (Map.Entry<Long, Integer> en : cnt.entrySet()) {
                if (en.getValue() != 1) continue;
                int u = (int) (en.getKey() / 100000L);
                int v = (int) (en.getKey() % 100000L);
                if (orient(pts.get(u), pts.get(v), pts.get(i)) < 0) {
                    int tmp = u; u = v; v = tmp;
                }
                tris.add(new int[]{u, v, i});
            }
            cnt.clear();
        }
        List<int[]> out = new ArrayList<>();
        for (int[] t : tris)
            if (t[0] < n && t[1] < n && t[2] < n) out.add(t);
        return out;
    }

    public static void main(String[] args) {
        List<double[]> pts = new ArrayList<>(List.of(
            new double[]{2, 1}, new double[]{4, 6}, new double[]{7, 2},
            new double[]{1, 5}, new double[]{6, 7}));
        for (int[] t : delaunay(pts))
            System.out.println("triangle: " + t[0] + " " + t[1] + " " + t[2]);
    }
}
