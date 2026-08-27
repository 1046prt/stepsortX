// Stepsort · Count-Min Sketch
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/count-min-sketch

public class CountMinSketch {
    int width, depth;
    int[][] table;
    int[][] hashes;

    CountMinSketch(int width, int depth) {
        this.width = width;
        this.depth = depth;
        table = new int[depth][width];
        hashes = new int[depth][];
        for (int i = 0; i < depth; i++) {
            final int idx = i;
            hashes[i] = new int[]{7 * (idx + 1), 3 * idx}; // a, b for hash
        }
    }

    int hash(int item, int d) {
        return Math.abs((item * hashes[d][0] + hashes[d][1]) % width);
    }

    void update(int item) {
        for (int d = 0; d < depth; d++)
            table[d][hash(item, d)]++;
    }

    int query(int item) {
        int min = Integer.MAX_VALUE;
        for (int d = 0; d < depth; d++)
            min = Math.min(min, table[d][hash(item, d)]);
        return min;
    }

    public static void main(String[] args) {
        CountMinSketch cms = new CountMinSketch(8, 3);
        for (int item : new int[]{3, 1, 4, 1, 5, 9, 2, 6})
            cms.update(item);
        System.out.println("estimate for 1: " + cms.query(1));
        System.out.println("estimate for 9: " + cms.query(9));
    }
}
