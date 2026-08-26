// sortsort · Linear Probing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-linear-probing

public class Main {
    static final int CAP = 11;
    static final int EMPTY = Integer.MIN_VALUE;
    static final int DELETED = Integer.MIN_VALUE + 1; // tombstone

    static int[] keys = new int[CAP];
    static String[] values = new String[CAP];

    static int insert(int key, String value) {
        int firstDead = -1;
        for (int step = 0; step < CAP; step++) {
            int slot = (key % CAP + step) % CAP;
            if (keys[slot] == EMPTY) {
                int target = firstDead == -1 ? slot : firstDead;
                keys[target] = key;
                values[target] = value;
                return step + 1; // probes used
            }
            if (keys[slot] == DELETED) {
                if (firstDead == -1) firstDead = slot;
            } else if (keys[slot] == key) {
                values[slot] = value;
                return step + 1;
            }
        }
        return -1;
    }

    static int find(int key) {
        for (int step = 0; step < CAP; step++) {
            int slot = (key % CAP + step) % CAP;
            if (keys[slot] == EMPTY) return -1;
            if (keys[slot] == key) return slot;
        }
        return -1;
    }

    public static void main(String[] args) {
        java.util.Arrays.fill(keys, EMPTY);
        System.out.println("insert 22 -> " + insert(22, "V") + " probes");
        System.out.println("insert 33 -> " + insert(33, "G") + " probes");
        System.out.println("insert 44 -> " + insert(44, "S") + " probes");
        System.out.println("search 33 -> slot " + find(33));
        keys[find(33)] = DELETED;
        System.out.println("search 33 after delete -> slot " + find(33));
        System.out.println("insert 55 -> " + insert(55, "F")
                + " probes (tombstone reused)");
        StringBuilder sb = new StringBuilder("slots:");
        for (int i = 0; i < CAP; i++) {
            if (keys[i] == EMPTY) sb.append(" _");
            else if (keys[i] == DELETED) sb.append(" #");
            else sb.append(" ").append(keys[i]);
        }
        System.out.println(sb);
    }
}
