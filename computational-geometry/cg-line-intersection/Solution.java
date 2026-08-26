// sortsort · Line Intersection
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-line-intersection

public class Main {
    static double direction(double[] a, double[] b, double[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
    }

    static boolean onSegment(double[] a, double[] b, double[] p) {
        return Math.min(a[0], b[0]) <= p[0] && p[0] <= Math.max(a[0], b[0]) &&
               Math.min(a[1], b[1]) <= p[1] && p[1] <= Math.max(a[1], b[1]);
    }

    static boolean segmentsIntersect(double[] p1, double[] p2, double[] p3, double[] p4) {
        double d1 = direction(p3, p4, p1);
        double d2 = direction(p3, p4, p2);
        double d3 = direction(p1, p2, p3);
        double d4 = direction(p1, p2, p4);
        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
            ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) return true;
        if (d1 == 0 && onSegment(p3, p4, p1)) return true;
        if (d2 == 0 && onSegment(p3, p4, p2)) return true;
        if (d3 == 0 && onSegment(p1, p2, p3)) return true;
        if (d4 == 0 && onSegment(p1, p2, p4)) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println("s1 x s2: " +
            segmentsIntersect(new double[]{0, 0}, new double[]{4, 4},
                              new double[]{0, 4}, new double[]{4, 0}));
        System.out.println("s1 x s3: " +
            segmentsIntersect(new double[]{0, 0}, new double[]{4, 4},
                              new double[]{6, 6}, new double[]{8, 8}));
    }
}
