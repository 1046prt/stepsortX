// Stepsort · Voronoi Diagram
// Category: Computational Geometry
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cg-voronoi

public class Main {
    public static void main(String[] args) {
        double[] sx = {4, 16, 10};
        double[] sy = {16, 16, 4};
        char[] labels = {'A', 'B', 'C'};
        StringBuilder out = new StringBuilder();
        for (int gy = 20; gy >= 0; gy--) {
            for (int gx = 0; gx <= 20; gx++) {
                int best = 0;
                double bd = Double.MAX_VALUE;
                for (int i = 0; i < 3; i++) {
                    double dx = gx - sx[i], dy = gy - sy[i];
                    double d = dx * dx + dy * dy;
                    if (d < bd) { bd = d; best = i; }
                }
                out.append(labels[best]);
            }
            out.append(System.lineSeparator());
        }
        System.out.print(out);
    }
}
