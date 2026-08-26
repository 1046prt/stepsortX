// sortsort · Huffman Coding
// Category: Greedy
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/huffman-coding

import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    static void collectCodes(Node node, String prefix, Map<Character, String> codes) {
        if (node.left == null && node.right == null) {
            codes.put(node.ch, prefix.isEmpty() ? "0" : prefix);
            return;
        }
        collectCodes(node.left, prefix + "0", codes);
        collectCodes(node.right, prefix + "1", codes);
    }

    public static void main(String[] args) {
        String text = "huffman coding example";
        Map<Character, Integer> freq = new TreeMap<>();
        for (char c : text.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // merge the two least frequent nodes until one tree remains
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node merged = new Node((char) 0, left.freq + right.freq);
            merged.left = left;
            merged.right = right;
            pq.add(merged);
        }

        Map<Character, String> codes = new TreeMap<>();
        collectCodes(pq.poll(), "", codes);
        System.out.println("Huffman codes:");
        int totalBits = 0;
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("[" + entry.getKey() + "] -> " + entry.getValue());
            totalBits += entry.getValue().length() * freq.get(entry.getKey());
        }
        System.out.println("Encoded length in bits: " + totalBits);
    }
}
