// sortsort · Rotating Calipers
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-rotating-calipers

public class Main {
    static double cross(double[] o, double[] a, double[] b) {
        return (a[0] - o[0]) * (b[1] - o[1]) - (a[1] - o[1]) * (b[0] - o[0]);
    }

    static double dist2(double[] a, double[] b) {
        double dx = a[0] - b[0], dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

    static double convexDiameter(double[][] hull) {
        int n = hull.length;
        int j = 1;
        double best = 0;
        for (int i = 0; i < n; i++) {
            int ni = (i + 1) % n;
            while (true) {
                int nj = (j + 1) % n;
                if (cross(hull[i], hull[ni], hull[nj]) > cross(hull[i], hull[ni], hull[j]))
                    j = nj;
                else
                    break;
            }
            best = Math.max(best, Math.max(dist2(hull[i], hull[j]), dist2(hull[ni], hull[j])));
        }
        return Math.sqrt(best);
    }

    public static void main(String[] args) {
        double[][] hull = {{0, 0}, {4, 0}, {4, 3}, {0, 3}};
        System.out.println("diameter: " + convexDiameter(hull));
    }
}
