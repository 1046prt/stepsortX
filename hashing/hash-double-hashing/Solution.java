// Stepsort · Double Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-double-hashing

public class Main {
    static final int CAP = 11;

    static int h1(int key) { return key % CAP; }

    static int h2(int key) { return 1 + (key % (CAP - 1)); }

    static int insert(int[] keys, String[] values, int key, String value) {
        int base = h1(key), stride = h2(key);
        for (int i = 0; i < CAP; i++) {
            int slot = (base + i * stride) % CAP;
            if (keys[slot] == Integer.MIN_VALUE || keys[slot] == key) {
                keys[slot] = key;
                values[slot] = value;
                return i;
            }
        }
        return -1;
    }

    static int search(int[] keys, int key) {
        int base = h1(key), stride = h2(key);
        for (int i = 0; i < CAP; i++) {
            int slot = (base + i * stride) % CAP;
            if (keys[slot] == Integer.MIN_VALUE) return -1;
            if (keys[slot] == key) return slot;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] keys = new int[CAP];
        String[] values = new String[CAP];
        java.util.Arrays.fill(keys, Integer.MIN_VALUE);
        int r = insert(keys, values, 10, "A");
        System.out.println("insert 10 -> stride " + h2(10) + ", attempts " + (r + 1));
        r = insert(keys, values, 21, "B");
        System.out.println("insert 21 -> stride " + h2(21) + ", attempts " + (r + 1));
        r = insert(keys, values, 32, "C");
        System.out.println("insert 32 -> stride " + h2(32) + ", attempts " + (r + 1));
        int[] queries = {10, 21, 32, 54};
        for (int k : queries) {
            System.out.println("search " + k + " -> slot " + search(keys, k));
        }
    }
}
