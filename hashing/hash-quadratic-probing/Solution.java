// Stepsort · Quadratic Probing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-quadratic-probing

public class Main {
    static final int CAP = 11;

    static int insert(int[] keys, String[] values, int key, String value) {
        for (int i = 0; i < CAP; i++) {
            int slot = (key % CAP + i * i) % CAP;
            if (keys[slot] == Integer.MIN_VALUE || keys[slot] == key) {
                keys[slot] = key;
                values[slot] = value;
                return i + 1;
            }
        }
        return -1;
    }

    static int search(int[] keys, int key) {
        for (int i = 0; i < CAP; i++) {
            int slot = (key % CAP + i * i) % CAP;
            if (keys[slot] == Integer.MIN_VALUE) return -1;
            if (keys[slot] == key) return slot;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] keys = new int[CAP];
        String[] values = new String[CAP];
        java.util.Arrays.fill(keys, Integer.MIN_VALUE);
        System.out.println("insert 10 -> " + insert(keys, values, 10, "A") + " probes");
        System.out.println("insert 21 -> " + insert(keys, values, 21, "B") + " probes");
        System.out.println("insert 32 -> " + insert(keys, values, 32, "C") + " probes");
        System.out.println("insert 43 -> " + insert(keys, values, 43, "D") + " probes");
        int[] queries = {10, 21, 32, 43, 54};
        for (int k : queries) {
            int slot = search(keys, k);
            if (slot == -1) System.out.println("search " + k + " -> not found");
            else System.out.println("search " + k + " -> slot "
                    + slot + " (" + values[slot] + ")");
        }
    }
}
