// Stepsort · Polygon Area (Shoelace)
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-polygon-area

public class Main {
    static double polygonArea(double[][] poly) {
        double s = 0;
        int n = poly.length;
        for (int i = 0; i < n; i++) {
            double[] a = poly[i];
            double[] b = poly[(i + 1) % n];
            s += a[0] * b[1] - b[0] * a[1];
        }
        return Math.abs(s) / 2.0;
    }

    public static void main(String[] args) {
        double[][] poly = {{0, 0}, {4, 0}, {4, 3}, {0, 3}};
        System.out.println("area: " + polygonArea(poly));
    }
}
