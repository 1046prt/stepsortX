// sortsort · Consistent Hashing
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/consistent-hashing

import java.util.Arrays;

public class Main {
    static long hashText(String text) {
        final long MASK = 0xffffffffL;
        long value = 0;
        for (char ch : text.toCharArray()) value = (value * 131 + ch) & MASK;
        value ^= (value >>> 16);           // avalanche so nearby names spread out
        value = (value * 0x45d9f3bL) & MASK;
        value ^= (value >>> 16);
        value = (value * 0x45d9f3bL) & MASK;
        value ^= (value >>> 16);
        return value;
    }

    static class HashRing {
        long[] hashes = new long[0];
        String[] servers = new String[0];

        void addServer(String name) {
            long hv = hashText(name);
            int n = hashes.length, lo = 0, hi = n;
            while (lo < hi) {  // insertion point keeps arrays sorted
                int mid = (lo + hi) >>> 1;
                if (hashes[mid] < hv) lo = mid + 1; else hi = mid;
            }
            long[] nh = Arrays.copyOf(hashes, n + 1);
            String[] ns = new String[n + 1];
            for (int i = lo; i < n; i++) { nh[i + 1] = hashes[i]; ns[i + 1] = servers[i]; }
            nh[lo] = hv;
            ns[lo] = name;
            for (int i = 0; i < lo; i++) ns[i] = servers[i];
            hashes = nh;
            servers = ns;
        }

        String getServer(long key) {
            long kh = hashText(Long.toString(key));
            int lo = 0, hi = hashes.length;
            while (lo < hi) {  // first hash >= kh via binary search
                int mid = (lo + hi) >>> 1;
                if (hashes[mid] < kh) lo = mid + 1; else hi = mid;
            }
            return servers[lo % servers.length];  // wrap around the ring
        }
    }

    public static void main(String[] args) {
        HashRing ring = new HashRing();
        ring.addServer("alpha");
        ring.addServer("bravo");
        ring.addServer("charlie");
        long[] keys = {101, 202, 303, 404, 505, 606};
        String[] before = new String[keys.length];
        System.out.println("initial ring:");
        for (int i = 0; i < keys.length; i++) {
            before[i] = ring.getServer(keys[i]);
            System.out.println("  key " + keys[i] + " -> " + before[i]);
        }
        ring.addServer("delta");
        System.out.println("after adding delta:");
        int moved = 0;
        for (int i = 0; i < keys.length; i++) {
            String now = ring.getServer(keys[i]);
            boolean changed = !now.equals(before[i]);
            if (changed) moved++;
            System.out.println("  key " + keys[i] + " -> " + now
                    + (changed ? " (moved)" : ""));
        }
        System.out.println("remapped " + moved + " of " + keys.length + " keys");
    }
}
