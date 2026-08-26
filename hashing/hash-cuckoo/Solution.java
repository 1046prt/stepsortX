// Stepsort · Cuckoo Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-cuckoo

public class Main {
    static class CuckooTable {
        static final int MAX_KICKS = 8;
        int[] t1, t2;
        int cap;

        CuckooTable(int cap) {
            this.cap = cap;
            t1 = new int[cap];
            t2 = new int[cap];
            java.util.Arrays.fill(t1, -1);
            java.util.Arrays.fill(t2, -1);
        }

        int h1(int key) { return key % cap; }
        int h2(int key) { return (key / cap) % cap; }

        boolean lookup(int key) {
            return t1[h1(key)] == key || t2[h2(key)] == key;
        }

        void insert(int key) {
            for (int round = 0; round < MAX_KICKS; round++) {
                int pos = h1(key);
                if (t1[pos] == -1) { t1[pos] = key; return; }
                int victim = t1[pos];
                t1[pos] = key;
                key = victim;
                System.out.println("  kick " + key + " out of T1 slot " + pos);
                pos = h2(key);
                if (t2[pos] == -1) { t2[pos] = key; return; }
                victim = t2[pos];
                t2[pos] = key;
                key = victim;
                System.out.println("  kick " + key + " out of T2 slot " + pos);
            }
            System.out.println("  eviction limit reached: rehashing");
            rehash();
            insert(key);
        }

        private void rehash() {
            int[] old = new int[t1.length + t2.length];
            int n = 0;
            for (int k : t1) if (k != -1) old[n++] = k;
            for (int k : t2) if (k != -1) old[n++] = k;
            cap *= 2;
            t1 = new int[cap];
            t2 = new int[cap];
            java.util.Arrays.fill(t1, -1);
            java.util.Arrays.fill(t2, -1);
            System.out.println("  rehash with capacity " + cap);
            for (int i = 0; i < n; i++) insert(old[i]);
        }
    }

    public static void main(String[] args) {
        CuckooTable ct = new CuckooTable(4);
        int[] items = {4, 8, 12, 1, 5};
        for (int k : items) {
            System.out.println("insert " + k);
            ct.insert(k);
        }
        int[] queries = {4, 8, 12, 1, 5, 99};
        for (int k : queries) {
            System.out.println("lookup " + k + " -> " + ct.lookup(k));
        }
    }
}
