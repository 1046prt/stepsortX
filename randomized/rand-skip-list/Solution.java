// sortsort · Skip List
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-skip-list

public class Main {
    static final int MAX_LEVEL = 4;   // cap on tower height
    static final double P = 0.5;      // probability of growing one extra level
    static java.util.Random rand = new java.util.Random(42);

    static class Node {
        int key;
        Node[] forward;  // next node at each level
        Node(int key, int level) {
            this.key = key;
            this.forward = new Node[level + 1];
        }
    }

    static Node header = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    static int level = 0;

    static int randomLevel() {
        int lvl = 0;
        while (rand.nextDouble() < P && lvl < MAX_LEVEL) lvl++;
        return lvl;
    }

    static boolean search(int key) {
        Node node = header;
        for (int i = level; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].key < key)
                node = node.forward[i];
        }
        Node next = node.forward[0];
        return next != null && next.key == key;
    }

    static boolean insert(int key) {
        Node[] update = new Node[MAX_LEVEL + 1];
        java.util.Arrays.fill(update, header);
        Node node = header;
        for (int i = level; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].key < key)
                node = node.forward[i];
            update[i] = node;
        }
        Node next = node.forward[0];
        if (next != null && next.key == key) return false;
        int lvl = randomLevel();
        if (lvl > level) level = lvl;
        Node created = new Node(key, lvl);
        for (int i = 0; i <= lvl; i++) {
            created.forward[i] = update[i].forward[i];
            update[i].forward[i] = created;
        }
        return true;
    }

    static boolean remove(int key) {
        Node[] update = new Node[MAX_LEVEL + 1];
        java.util.Arrays.fill(update, header);
        Node node = header;
        for (int i = level; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].key < key)
                node = node.forward[i];
            update[i] = node;
        }
        Node target = node.forward[0];
        if (target == null || target.key != key) return false;
        for (int i = 0; i <= level; i++) {
            if (update[i].forward[i] == target)
                update[i].forward[i] = target.forward[i];
        }
        while (level > 0 && header.forward[level] == null) level--;
        return true;
    }

    public static void main(String[] args) {
        for (int value : new int[]{10, 30, 20, 50, 40}) insert(value);
        System.out.println("search 20: " + search(20));
        System.out.println("search 60: " + search(60));
        remove(30);
        System.out.println("search 30 after removal: " + search(30));
        Node node = header.forward[0];
        while (node != null) {
            System.out.println("key: " + node.key + " height: "
                    + (node.forward.length - 1));
            node = node.forward[0];
        }
    }
}
