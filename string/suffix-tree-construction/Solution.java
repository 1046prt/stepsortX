// Stepsort · Suffix Tree (Compressed)
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-tree-construction

import java.util.*;

public class Main {
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isLeaf = false;
    }

    public static void main(String[] args) {
        String s = "banana";
        Node root = new Node();
        int nodeCount = 1, leafCount = 0;
        for (int k = 1; k <= s.length(); k++) {
            String suffix = s.substring(s.length() - k);
            Node cur = root;
            for (char c : suffix.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Node());
                    nodeCount++;
                }
                cur = cur.children.get(c);
            }
            if (!cur.isLeaf) {
                cur.isLeaf = true;
                leafCount++;
            }
        }
        System.out.println("nodes: " + nodeCount + ", leaves: " + leafCount);
    }
}
