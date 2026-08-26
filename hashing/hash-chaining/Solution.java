// Stepsort · Chaining
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/hash-chaining

import java.util.LinkedList;

public class Main {
    static class ChainHashTable {
        private static class Pair {
            int key;
            String value;
            Pair(int key, String value) { this.key = key; this.value = value; }
        }

        private final LinkedList<Pair>[] buckets;

        @SuppressWarnings("unchecked")
        ChainHashTable(int capacity) {
            buckets = new LinkedList[capacity];
            for (int i = 0; i < capacity; i++) buckets[i] = new LinkedList<>();
        }

        private int indexOf(int key) { return Math.floorMod(key, buckets.length); }

        void insert(int key, String value) {
            for (Pair p : buckets[indexOf(key)]) {
                if (p.key == key) { p.value = value; return; }
            }
            buckets[indexOf(key)].add(new Pair(key, value));
        }

        String search(int key) {
            for (Pair p : buckets[indexOf(key)]) {
                if (p.key == key) return p.value;
            }
            return null;
        }

        boolean remove(int key) {
            LinkedList<Pair> chain = buckets[indexOf(key)];
            for (int i = 0; i < chain.size(); i++) {
                if (chain.get(i).key == key) { chain.remove(i); return true; }
            }
            return false;
        }

        void printTable() {
            for (int i = 0; i < buckets.length; i++) {
                StringBuilder sb = new StringBuilder("bucket " + i + " ->");
                for (Pair p : buckets[i]) {
                    sb.append(" (").append(p.key).append(",").append(p.value).append(")");
                }
                if (buckets[i].isEmpty()) sb.append(" (empty)");
                System.out.println(sb);
            }
        }
    }

    public static void main(String[] args) {
        ChainHashTable ht = new ChainHashTable(7);
        ht.insert(10, "A");
        ht.insert(17, "B"); // 10 and 17 share bucket 3
        ht.insert(24, "C");
        ht.insert(5, "D");
        System.out.println("search 17 -> " + ht.search(17));
        System.out.println("search 99 -> " + ht.search(99));
        System.out.println("delete 17 -> " + ht.remove(17));
        System.out.println("delete 99 -> " + ht.remove(99));
        ht.printTable();
    }
}
