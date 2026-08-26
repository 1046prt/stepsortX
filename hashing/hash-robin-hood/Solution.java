// Stepsort · Robin Hood Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-robin-hood

public class Main {
    static final int CAP = 11;

    static class Slot {
        int key;
        int dist = -1; // -1 marks empty

        Slot() {}
        Slot(int key, int dist) { this.key = key; this.dist = dist; }
    }

    static Slot[] slots = new Slot[CAP];

    static int insert(int key) {
        Slot entry = new Slot(key, 0);
        int pos = key % CAP;
        while (true) {
            Slot cur = slots[pos];
            if (cur.dist == -1) { slots[pos] = entry; return entry.dist; }
            if (cur.key == key) return -1; // duplicate ignored
            if (cur.dist < entry.dist) { // swap with richer resident
                Slot tmp = entry;
                entry = slots[pos];
                slots[pos] = tmp;
            }
            entry.dist++;
            pos = (pos + 1) % CAP;
        }
    }

    static int search(int key) {
        int base = key % CAP;
        for (int dist = 0; dist < CAP; dist++) {
            Slot cur = slots[(base + dist) % CAP];
            if (cur.dist == -1 || cur.dist < dist) return -1;
            if (cur.key == key) return (base + dist) % CAP;
        }
        return -1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < CAP; i++) slots[i] = new Slot();
        int[] items = {10, 20, 30, 42, 52};
        for (int k : items) {
            System.out.println("insert " + k + " -> settled at distance "
                    + insert(k));
        }
        System.out.println("table layout:");
        for (int i = 0; i < CAP; i++) {
            if (slots[i].dist == -1) System.out.println("  slot " + i + " -> (empty)");
            else System.out.println("  slot " + i + " -> key "
                    + slots[i].key + " distance " + slots[i].dist);
        }
        System.out.println("search 42 -> slot " + search(42));
        System.out.println("search 77 -> slot " + search(77) + " (-1 = absent)");
    }
}
