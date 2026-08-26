// Stepsort · Point in Polygon
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-point-in-polygon

public class Main {
    static boolean pointInPolygon(double[] p, double[][] poly) {
        boolean inside = false;
        int n = poly.length;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            boolean yi = poly[i][1] > p[1], yj = poly[j][1] > p[1];
            if (yi != yj) {
                double xc = (poly[j][0] - poly[i][0]) * (p[1] - poly[i][1]) /
                            (poly[j][1] - poly[i][1]) + poly[i][0];
                if (p[0] < xc) inside = !inside;
            }
        }
        return inside;
    }

    public static void main(String[] args) {
        double[][] square = {{0, 0}, {4, 0}, {4, 4}, {0, 4}};
        System.out.println("(2,2) inside: " + pointInPolygon(new double[]{2, 2}, square));
        System.out.println("(5,2) inside: " + pointInPolygon(new double[]{5, 2}, square));
    }
}
